import React, { useState } from 'react';
import '../styles.css'; // Создайте CSS для стилизации

const ScrollingList = ({ data }) => {
    const [selectedItem, setSelectedItem] = useState(null);

    const handleItemClick = (item) => {
        setSelectedItem(item);
    };

    return (
        <div className="scrolling-list">
            <div className="items w-96 flex row">
                {data.map((item, index) => (
                    <div
                        key={index}
                        className={`item ${selectedItem === item ? 'selected' : ''}`}
                        onClick={() => handleItemClick(item)}
                    >
                        <div>Тип предприятия: {item.departmentType}</div>
                        <div>Адрес: {item.address}</div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ScrollingList;