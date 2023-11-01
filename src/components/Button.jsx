import React from 'react';

function SubmitButton({ filterData }) {
    const handleClick = () => {
        // Формируем URL для GET-запроса на основе данных из filterData
        const queryParams = new URLSearchParams(filterData);
        const url = `/api/offices?${queryParams.toString()}`;

        fetch(url)
            .then((response) => response.json())
            .then((data) => {
                // Обрабатываем полученные данные
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