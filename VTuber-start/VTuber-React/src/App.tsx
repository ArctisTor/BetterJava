import { Route, createBrowserRouter, createRoutesFromElements, RouterProvider } from 'react-router-dom';

import './App.css';
import VTuberList from './pages/VTuberList';

function App() {
  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path="/">
        <Route index element={<VTuberList />} />
      </Route>
    )
  );

  return <RouterProvider router={router} />;
}

export default App;
