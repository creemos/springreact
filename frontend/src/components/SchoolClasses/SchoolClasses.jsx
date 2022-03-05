import { useState, useEffect } from "react";
import Loader from "./../Loader/Loader";
import axios from "axios";
import SchoolClassModal from "./SchoolClassModal";

const SchoolClasses = () => {
  const [allSchoolClasses, setAllSchoolClasses] = useState([]);
  const [isShowSchoolClassModal, setIsShowSchoolClassModal] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [editMode, setEditMode] = useState(false);
  const [currentSchoolClass, setCurrentSchoolClass] = useState({
    id: "",
    code: "",
    year: "",
    students: [],
    teacher: {},
  });

  const fetchAllSchoolClasses = () => {
    axios
      .get("http://localhost:9090/api/classes")
      .then((res) => setAllSchoolClasses(res.data))
      .then(setIsLoading(false));
  };

  useEffect(() => {
    fetchAllSchoolClasses();
  }, []);

  useEffect(() => {
    if (isShowSchoolClassModal === false || isLoading === false) {
      fetchAllSchoolClasses();
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [isShowSchoolClassModal]);

  const editSchoolClass = (id) => {
    console.log(`Select class no.${id}`);
    setIsLoading(true);
    setEditMode(true);

    axios
      .get(`http://localhost:9090/api/classes/${id}`)
      .then((res) => setCurrentSchoolClass(res.data))
      .then(setIsShowSchoolClassModal(true));
  };

  const deleteSchoolClass = (id) => {
    setIsLoading(true);
    axios
      .delete(`http://localhost:9090/api/classes/${id}`)
      .then((res) => {
        fetchAllSchoolClasses();
        console.log(`Class with no.${id} deleted!`);
      })
      .then(setIsLoading(false));
  };

  const onSubmit = (data) => {
    if (!editMode) {
      axios
        .post("http://localhost:9090/api/classes", data, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "content-type": "application/json",
          },
        })
        .then((res) =>
          console.log(
            `Adding class ${res.data.year}${res.data.code} with id.${res.data.id}`
          )
        )
        .catch((err) => console.log(err));
    } else {
      axios.put(
        `http://localhost:9090/api/classes/${currentSchoolClass.id}`,
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
    setIsShowSchoolClassModal(false);
  };

  useEffect(() => {
    if (currentSchoolClass !== 1 && isShowSchoolClassModal === true) {
      setIsLoading(false);
    }
  }, [currentSchoolClass, isShowSchoolClassModal]);

  return (
    <div className="w-full flex flex-col items-center justify-between">
      {isLoading ? (
        <Loader />
      ) : isShowSchoolClassModal === false ? (
        <div className="w-full">
          <table className="text-center border-2 mt-5 w-full">
            <thead className="bg-slate-400">
              <tr>
                <th>Год обучения</th>
                <th>Мнемокод</th>
                <th>Классный руководитель</th>
                <th>Список учеников</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {allSchoolClasses.map((schoolClass) => {
                return (
                  <tr key={schoolClass.id}>
                    <td>{schoolClass.year}</td>
                    <td>{schoolClass.code}</td>
                    <td>
                      {schoolClass.teacher
                        ? `${schoolClass.teacher.firstname} ${schoolClass.teacher.patronymic} ${schoolClass.teacher.lastname}`
                        : ""}
                    </td>
                    <td>
                      <ul>
                        {schoolClass.students.map((student) => {
                          return (
                            <li key={student.student_id} className="ml-5 mr-5">
                              {`${student.firstname} ${student.patronymic} ${student.lastname}`}
                            </li>
                          );
                        })}
                      </ul>
                    </td>
                    <td>
                      <button
                        className="self-center bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                        onClick={() => {
                          editSchoolClass(schoolClass.id);
                        }}
                      >
                        Изменить
                      </button>
                      <button
                        className="self-center bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                        onClick={() => {deleteSchoolClass(schoolClass.id)}}
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
          onClick={() => setIsShowSchoolClassModal(true)}
          className="self-center mt-5 w-1/2  bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
          >Добавить класс</button>
        </div>
      ) : (
        <SchoolClassModal onSubmit={onSubmit} data={currentSchoolClass} />
      )}
    </div>
  );
};

export default SchoolClasses;
