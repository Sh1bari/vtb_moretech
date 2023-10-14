import MyMap from './components/MyMap';
import Container from './components/Container';
import './styles.css'

function App() {


  return (
    <div className="flex justify-center items-center 
    h-screen mx-auto my-auto text-white bg-custom-grey">
      <div>
        <Container />
        <MyMap />
      </div>
    </div>
  );
}

export default App;