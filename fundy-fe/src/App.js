import {BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Main from './pages/Main';
import Navigator from './components/Navigator';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navigator />
        <Routes>
          <Route path='/' element={<Main />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signup' element={<SignUp />} />
        </Routes>

      </div>
    </BrowserRouter>
  );
}

export default App;
