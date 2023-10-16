// Filter.jsx
import React, { useState } from 'react';

function Filter({ onFilterChange }) {
  const [hasRamp, setHasRamp] = useState(false);
  const [customerType, setCustomerType] = useState('');
  const [hour, setHour] = useState(null);

  const handleHasRampChange = () => {
    setHasRamp(!hasRamp);
  };

  const handleCustomerTypeChange = (event) => {
    const selectedValue = event.target.value;
    let newIsIndividual = selectedValue === 'physical' ? '' : selectedValue;
  
    setCustomerType(newIsIndividual);
  };
  
  const handleHourChange = (event) => {
    const newValue = parseInt(event.target.value, 10);
    if (!isNaN(newValue) && newValue >= 0 && newValue <= 24) {
      setHour(newValue);
    }
  };

  const applyFilter = () => {
    const filterData = {
      hasRamp: hasRamp ? 'Y' : 'N',
      isIndividual: customerType,
      hour,
    };
    onFilterChange(filterData);
  };

  return (
    <div>
      <label>
        Нужна ли рампа для слабопередвигающихся:
        <input
          type="checkbox"
          checked={hasRamp}
          onChange={handleHasRampChange}
        />
      </label>
      <br />
      <label>
        Тип клиента:
        <select
          value={customerType}
          onChange={handleCustomerTypeChange}
        >
          <option value="physical">Физическое лицо</option>
          <option value="N">Юридическое лицо</option>
          <option value="Y">ИП</option>
        </select>
      </label>
      <br />
      <label>
        Желаемый час посещения (от 0 до 24):
        <input
          type="number"
          value={hour}
          onChange={handleHourChange}
          min="0"
          max="24"
        />
      </label>
      <br />
      <button onClick={applyFilter}>Применить фильтр</button>
    </div>
  );
}

export default Filter;