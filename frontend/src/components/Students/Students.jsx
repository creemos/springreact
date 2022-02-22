import { useEffect, useState } from "react";
import axios from "axios";
import Loader from "./../Loader/Loader";
import StudentModal from "./StudentModal";

const Students = () => {
  const [allStudents, setAllStudents] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [showStudentModal, setShowStudentModal] = useState(false);
  const [currentStudent, setCurrentStudent] = useState({
    firstname: "",
    patronymic: "",
    lastname: "",
    gender: "",
  });

  const fetchStudents = () => {
    setIsLoading(true);
    axios
      .get("http://localhost:9090/api/students")
      .then((res) => {
        setAllStudents(res.data);
      })
      .then(setIsLoading(false));
  };

  const deleteStudent = (id) => {
    setIsLoading(true);
    axios
      .delete(`http://localhost:9090/api/students/${id}`)
      .then((res) => {
        fetchStudents();
      })
      .then(setIsLoading(false));
  };

  const update = (user) => {
    console.log(user);
    setCurrentStudent((prevState) => {
      return { ...prevState, ...user};
    });
  };

  useEffect(() => {
    console.log(currentStudent)
  }, [currentStudent])

  const editStudent = (id) => {
    console.log(`Select student no.${id}`);
    setIsLoading(true);
    axios.get(`http://localhost:9090/api/students/${id}`).then((res) => {
      setIsLoading(false);
      setShowStudentModal(true);
      update({
        firstname: res.data.firstname,
        patronymic: res.data.patronymic,
        lastname: res.data.lastname,
        gender: res.data.gender,
      });
      console.log(currentStudent);
    });
  };

  useEffect(() => {
    fetchStudents();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [showStudentModal]);

  const onSubmit = (data) => {
    axios
      .post("http://localhost:9090/api/students", data, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "content-type": "application/json",
        },
      })
      .then((res) => console.log(res))
      .catch((err) => console.log(err))
      .then(fetchStudents());
    setShowStudentModal(false);
  };

  return (
    <div className="w-3/4 flex flex-col items-center justify-between">
      {isLoading ? (
        <Loader />
      ) : showStudentModal === false ? (
        <div className="ml-10 w-full flex flex-col justify-between">
          <table className="text-center border-2 mt-5">
            <thead className="bg-slate-400">
              <tr>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Фамилия</th>
                <th>Пол</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {allStudents.map((student) => {
                return (
                  <tr key={student.id}>
                    <td>{student.firstname}</td>
                    <td>{student.patronymic}</td>
                    <td>{student.lastname}</td>
                    <td>{student.gender}</td>
                    <td>
                      <button
                        className="self-center bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                        onClick={() => editStudent(student.id)}
                      >
                        Изменить
                      </button>
                      <button
                        className="self-center bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                        onClick={() => deleteStudent(student.id)}
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
            onClick={() => setShowStudentModal(true)}
            className="self-center mt-5 w-1/2  bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
          >
            Добавить ученика
          </button>
        </div>
      ) : (
        <StudentModal onSubmit={onSubmit} data={currentStudent} />
      )}
    </div>
  );
};

export default Students;
