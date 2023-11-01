import React from 'react';
import './SubmitButton.css'

function SubmitButton({ filterData }) {
    const handleClick = () => {
        const queryParams = new URLSearchParams(filterData);
        const url = `/api/offices?${queryParams.toString()}`;

        fetch(url)
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
            })
            .catch((error) => {
                console.error('Ошибка при выполнении GET-запроса', error);
            });
    };

    return (
        <button onClick={handleClick}>Отправить</button>
    );
}

export default SubmitButton;
