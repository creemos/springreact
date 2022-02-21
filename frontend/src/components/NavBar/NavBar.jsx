import { Link } from "react-router-dom"

const NavBar = () => {
    return (
        <div className="flex flex-col">
            <div className="m-5 text-2xl"><Link to="/teachers">Учительский состав</Link></div>
            <div className="m-5 text-2xl"><Link to="/students">Студенческий состав</Link></div>
        </div>
    )
}

export default NavBar