import { useForm } from "react-hook-form";
import axios from "axios";

const StudentModal = ({onSubmit}) => {
  const { register, handleSubmit } = useForm();
  
  return (
    <form className="mt-5" onSubmit={handleSubmit(onSubmit)}>
      <input placeholder="Имя" {...register("firstname", { required: true })} />
      <input placeholder="Отчество" {...register("patronymic", { required: true })} />
      <input placeholder="Фамилия" {...register("lastname", { required: true })} />
      <select {...register("gender")}>
        <option value="жен.">жен.</option>
        <option value="муж.">муж.</option>
      </select>
      <input className="self-center mt-5 w-1/2  bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="submit" />
    </form>
  );
};

export default StudentModal