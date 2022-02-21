import { useEffect, useState } from "react";
import axios from "axios";
import Loader from "./../Loader/Loader";

const Students = () => {
  const [allStudents, setAllStudents] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setIsLoading(true);
    axios.get("http://localhost:9090/api/students").then((res) => {
      setAllStudents(res.data);
      console.log(allStudents);
      setIsLoading(false);
    });
  }, []);

  return (
    <div className="w-3/4">
      {isLoading ? (
        <Loader />
      ) : (
        <table className="ml-20 w-1/2 text-center border-2">
          <thead>
            <tr>
              <th>Имя</th>
              <th>Отчество</th>
              <th>Фамилия</th>
              <th>Пол</th>
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
                </tr>
              );
            })}
          </tbody>

          <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Добавить ученика
          </button>
        </table>
      )}
    </div>
  );
};

export default Students;
