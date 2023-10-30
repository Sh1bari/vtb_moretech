import React from 'react';

function ChooseRoute({ onRouteTypeChange, setMultiRoute }) {
    const handleRouteClick = (routeType) => {
        onRouteTypeChange(routeType);
    };

    return (
        <div>
            <button onClick={() => handleRouteClick('auto')}>Автомобиль</button>
            <button onClick={() => handleRouteClick('masstransit')}>Автобусы</button>
            <button onClick={() => handleRouteClick('pedestrian')}>Пешеход</button>
        </div>
    );
}

export default ChooseRoute;