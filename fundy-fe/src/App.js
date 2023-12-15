import {BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import Navigator from './components/common/Navigator';
import { DevNotes, Login, Main, MyPage, ProjectDetail, SignUp } from './pages';


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
