import { useForm } from "react-hook-form";
import {useEffect} from 'react'

const StudentModal = ({onSubmit}, data) => {
  
  useEffect(()=>{
    console.log(data)
  }, [data])
  const { register, handleSubmit } = useForm();
  return (
    <form className="mt-5 border-1" onSubmit={handleSubmit(onSubmit)}>
      <input name="firstname" placeholder="Имя" {...register("firstname", { required: true })} className="m-5 p-2 border border-1" />
      <input placeholder="Отчество" {...register("patronymic", { required: true })} className="m-5 p-2 border border-1" />
      <input placeholder="Фамилия" {...register("lastname", { required: true })} className="m-5 p-2 border border-1" />
      <select {...register("gender")} placeholder="Пол">
        <option value="жен.">жен.</option>
        <option value="муж.">муж.</option>
      </select>
      <input className="mt-5 w-1/2  bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="submit" />
    </form>
  );
};

export default StudentModal