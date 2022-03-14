import { useEffect, useState } from "react";
import axios from "axios";
import Loader from "./../Loader/Loader";
import TeacherModal from "./TeacherModal";

const Teachers = () => {
  const [allTeachers, setAllTeachers] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [showTeacherModal, setShowTeacherModal] = useState(false);
  const [editMode, setEditMode] = useState(false);
  const [currentTeacher, setCurrentTeacher] = useState({
    id: "",
    firstname: "",
    patronymic: "",
    lastname: "",
    gender: "",
    year: "",
    subject: "",
  });

  const fetchTeachers = () => {
    setIsLoading(true);
    axios
      .get("http://localhost:9090/api/teachers")
      .then((res) => {
        setAllTeachers(res.data);
      })
      .then(setIsLoading(false));
  };

  const deleteTeacher = async(id) => {
    setIsLoading(true);
    await axios.put(`http://localhost:9090/api/classes/find_relation`, id, {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "content-type": "application/json",
      },
    })
    await axios
      .delete(`http://localhost:9090/api/teachers/${id}`)
      .then((res) => {
        fetchTeachers();
        console.log(`User with no.${id} deleted!`);
      })
      .then(setIsLoading(false))
  };

  useEffect(() => {
    if (currentTeacher !== 1 && showTeacherModal === true) {
      setIsLoading(false);
    }
  }, [currentTeacher, showTeacherModal]);

  const editTeacher = (id) => {
    console.log(`Select teacher no.${id}`);
    setIsLoading(true);
    setEditMode(true);
    axios
      .get(`http://localhost:9090/api/teachers/${id}`)
      .then((res) => setCurrentTeacher(res.data))
      .then(setShowTeacherModal(true));
  };

  useEffect(() => {
    if (showTeacherModal === false) {
      fetchTeachers();
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [showTeacherModal]);

  const onSubmit = (data) => {
    if (!editMode) {
      axios
        .post("http://localhost:9090/api/teachers", data, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "content-type": "application/json",
          },
        })
        .then((res) =>
          console.log(`Adding user ${res.data.firstname} no.${res.data.id}`)
        )
        .catch((err) => console.log(err));
    } else {
      axios.put(
        `http://localhost:9090/api/teachers/${currentTeacher.id}`,
        data,
        {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "content-type": "application/json",
          },
        }
      );
      setEditMode(false);
    }
    setShowTeacherModal(false);
    fetchTeachers()
  };

  return (
      <div className="w-3/4 flex flex-col items-center justify-between">
        {isLoading ? (
          <Loader />
        ) : showTeacherModal === false ? (
          <div className="ml-10 w-full flex flex-col justify-between">
            <table className="text-center border-2 mt-5">
              <thead className="bg-slate-400">
                <tr>
                  <th>Имя</th>
                  <th>Отчество</th>
                  <th>Фамилия</th>
                  <th>Пол</th>
                  <th>Год рождения</th>
                  <th>Предмет</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                {allTeachers.map((teacher) => {
                  return (
                    <tr key={teacher.id}>
                      <td>{teacher.firstname}</td>
                      <td>{teacher.patronymic}</td>
                      <td>{teacher.lastname}</td>
                      <td>{teacher.gender}</td>
                      <td>{teacher.year}</td>
                      <td>{teacher.subject}</td>
                      <td>
                        <button
                          className="self-center bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                          onClick={() => editTeacher(teacher.id)}
                        >
                          Изменить
                        </button>
                        <button
                          className="self-center bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                          onClick={() => deleteTeacher(teacher.id)}
                        >
                          Удалить
                        </button>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
            <button
              onClick={() => setShowTeacherModal(true)}
              className="self-center mt-5 w-1/2  bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            >
              Добавить учителя
            </button>
          </div>
        ) : (
          <TeacherModal onSubmit={onSubmit} data={currentTeacher} />
        )}
      </div>
  );
};

export default Teachers;