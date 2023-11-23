import Login from "./components/Login";
import Map from "./components/Map";
import {BrowserRouter, Route, Routes} from "react-router-dom";
function App() {
  return (


    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path = "/" element = {<Login />}></Route>
        <Route path = "/map" element = {<Map />}></Route>
      </Routes>

      </BrowserRouter>
    </div>

  );
}

export default App;
