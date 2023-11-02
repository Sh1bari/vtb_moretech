import React, { useState } from 'react'

function Filter() {
    const [radius, setRadius] = useState('');
    const [hasRamp, setHasRamp] = useState('N');
    const [hour, setHour] = useState('');
    const [isIndividual, setIsIndividual] = useState('N');

    const handleSubmit = (e) => {
        e.preventDefault();

        const filterData = {
            radius,
            hasRamp,
            hour,
            isIndividual,
        };
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="radius">Радиус поиска:</label>
                <input
                    type="number"
                    id="radius"
                    value={radius}
                    onChange={(e) => setRadius(e.target.value)}
                />
            </div>
            <div>
                <label htmlFor="hasRamp">Наличие рампы:</label>
                <select id="hasRamp" value={hasRamp} onChange={(e) => setHasRamp(e.target.value)}>
                    <option value="Y">Да</option>
                    <option value="N">Нет</option>
                </select>
            </div>
            <div>
                <label htmlFor="hour">Час посещения:</label>
                <input
                    type="number"
                    id="hour"
                    value={hour}
                    onChange={(e) => setHour(e.target.value)}
                />
            </div>
            <div>
                <label htmlFor="isIndividual">Тип лица:</label>
                <select id="isIndividual" value={isIndividual} onChange={(e) => setIsIndividual(e.target.value)}>
                    <option value="Y">Физическое лицо</option>
                    <option value="N">Юридическое лицо</option>
                </select>
            </div>
            <button type="submit">Отправить</button>
        </form>
    );
}


export default Filter