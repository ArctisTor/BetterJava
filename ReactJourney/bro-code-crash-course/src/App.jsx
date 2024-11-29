import Header from "./Header";
import Footer from "./Footer";
import Food from "./components/Food";
import Card from "./components/Card/Card";
import Button from "./components/Button/Button";
import Student from "./components/Student/Student";
import UserGreetings from "./components/UserGreetings/UserGreetings";

//css classes
import './components/Card/Card.css'

//props = read-only properties that are shared between components
//        A parent component can send data to a child component
//        <Component key=value />


//conditional rendering = allows you to control what gets rendered
//                        in your application based on certain conditions
//                        (show, hide, or change components)

function App() {
  return (
    <>
      <Header/>
      <Food/>
      <Card></Card>
      <Button></Button>
      <Student name="Spongebob" age={30} isStudent={true}/>
      <Student name="Patrick" age={42} isStudent={false}/>
      <Student name="Ageless" isStudent={true}/>
      <Student></Student>
      <UserGreetings isLoggedIn={true} userName="User_1"/>
      <UserGreetings/>
      <Footer/>
    </>
  );
}

export default App;
