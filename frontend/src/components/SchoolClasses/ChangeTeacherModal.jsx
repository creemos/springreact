import { useForm } from "react-hook-form";
import { useEffect, useState } from "react";
import Loader from "../Loader/Loader";
import axios from "axios";

const ChangeTeacherModal = ({ onSubmit, data }) => {
  const [isLoading, setIsLoading] = useState(true);
  const [allTeachers, setAllTeachers] = useState();
  const { register, handleSubmit } = useForm();

  const fetchTeacherData = () => {
    axios
      .get("http://localhost:9090/api/teachers")
      .then((res) => setAllTeachers(res.data))
      .then(setIsLoading(false))
  }

  useEffect(() => {
    fetchTeacherData()
  }, []);

  return isLoading ? (
    <Loader />
  ) : (
    <form className="mt-5 border-1" onSubmit={handleSubmit(onSubmit)}>
      <div>Выберите учителя</div>
      <select {...register("teacher")} placeholder="Выберите учителя">
        <option value={{}} className="border-2">
          Отсутствует
        </option>
        {allTeachers.map((teacher) => {
          return (
            <option
              key={teacher.id}
              value={JSON.stringify(teacher)}
              className="border-2"
            >
              {teacher.firstname} {teacher.patronymic} {teacher.lastname}
            </option>
          );
        })}
      </select>
      <input
        className="mt-5 w-1/2  bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        type="submit"
      />
    </form>
  );
};

export default ChangeTeacherModal;
