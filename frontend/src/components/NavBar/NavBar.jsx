import { Link } from "react-router-dom"

const NavBar = () => {
    return (
        <div className="flex flex-col">
            <div className="m-5 text-xl w-36"><Link to="/teachers">Учительский состав</Link></div>
            <div className="m-5 text-xl w-36"><Link to="/students">Студенческий состав</Link></div>
        </div>
    )
}

export default NavBar