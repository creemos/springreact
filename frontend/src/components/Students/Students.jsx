import { useEffect, useState } from "react";
import axios from "axios";
import Loader from "./../Loader/Loader";
import StudentModal from "./StudentModal";

const Students = () => {
  const [allStudents, setAllStudents] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [showStudentModal, setShowStudentModal] = useState(false);
  const [editMode, setEditMode] = useState(false);
  const [currentStudent, setCurrentStudent] = useState({
    studentId: "",
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
        console.log(`User with no.${id} deleted!`);
      })
      .then(setIsLoading(false));
  };

  useEffect(() => {
    if (currentStudent !== 1 && showStudentModal === true) {
      setIsLoading(false);
    }
  }, [currentStudent, showStudentModal]);

  const editStudent = (id) => {
    console.log(`Select student no.${id}`);
    setIsLoading(true);
    setEditMode(true);
    axios
      .get(`http://localhost:9090/api/students/${id}`)
      .then((res) => setCurrentStudent(res.data))
      .then(setShowStudentModal(true));
  };

  useEffect(() => {
    if (showStudentModal === false) {
      fetchStudents();
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [showStudentModal]);

  const onSubmit = async (data) => {
    if (!editMode) {
      await axios
        .post("http://localhost:9090/api/students", data, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "content-type": "application/json",
          },
        })
        .then((res) =>
          console.log(`Adding user ${res.data.firstname} no.${res.data.studentId}`)
        )
        .catch((err) => console.log(err));
    } else {
      await axios.put(
        `http://localhost:9090/api/students/${currentStudent.studentId}`,
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
                  <tr key={student.studentId}>
                    <td>{student.firstname}</td>
                    <td>{student.patronymic}</td>
                    <td>{student.lastname}</td>
                    <td>{student.gender}</td>
                    <td>
                      <button
                        className="self-center bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                        onClick={() => editStudent(student.studentId)}
                      >
                        Изменить
                      </button>
                      <button
                        className="self-center bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                        onClick={() => deleteStudent(student.studentId)}
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
