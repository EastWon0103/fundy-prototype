import {BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Main from './pages/Main';
import Navigator from './components/common/Navigator';
import MyPage from './pages/MyPage';
import ProjectDetail from './pages/ProjectDetail';
import DevNotes from './pages/DevNotes';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navigator />
        <Routes>
          <Route path='/' element={<Main />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signup' element={<SignUp />} />
          <Route path='/mypage' element={<MyPage />} />
          <Route path='/project/:id' element={<ProjectDetail />} />
          <Route path='/project/:id/devnotes' element={<DevNotes />} />
        </Routes>

      </div>
    </BrowserRouter>
  );
}

export default App;
