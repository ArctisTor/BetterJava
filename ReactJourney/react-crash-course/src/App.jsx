import React from 'react'
import { Route, createBrowserRouter, createRoutesFromElements, RouterProvider} from 'react-router-dom'

import HomePage from './pages/HomePage'
import JobsPage from './pages/JobsPage'
import NotFoundPage from './pages/NotFoundPage'

import MainLayout from './layouts/MainLayout'

function App() {

  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path='/' element={<MainLayout/>}> 
        <Route index element={<HomePage/>}/>
        <Route path='/jobs' element={<JobsPage/>}></Route>
        <Route path='*' element={<NotFoundPage/>}></Route>

      </Route>
    )
  );

  return (
    <RouterProvider router={router}/>
  );
}

export default App