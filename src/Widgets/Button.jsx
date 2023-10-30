// YourButtonComponent.js
import React from 'react';
import { useDispatch } from 'react-redux';
import { setLocation } from './locationReducer';

const Button = () => {
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Выполните GET-запрос и получите данные

    // Предположим, что полученные данные - это объект location
    const location = {
      latitude: 55.753600,
      longitude: 37.621184,
    };

    // Диспетчеризируйте действие для обновления местоположения
    dispatch(setLocation(location));
  };

  return (
    <button onClick={handleSubmit}>Отправить</button>
  );
};

export default Button;
