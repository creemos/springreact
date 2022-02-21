import { Routes, Route } from "react-router";
import Header from "./components/Header/Header";
import Teachers from "./components/Teachers/Teachers";
import Students from "./components/Students/Students";
import NavBar from './components/NavBar/NavBar';

function App() {
  return (
    <div className="App">
      <Header />
      <hr />
      <div className="flex">
        <NavBar />
        <Routes>
          <Route path="/teachers" element={<Teachers />}>
            
          </Route>
          <Route path="/students" element={<Students />}>
            
          </Route>
        </Routes>
      </div>
    </div>
  );
}

export default App;
