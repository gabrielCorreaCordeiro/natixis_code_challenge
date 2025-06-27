import { useState } from 'react';
import { FaTrash, FaEdit, FaSave, FaTimes } from 'react-icons/fa';

function ItemList({ items, onEdit, onDelete }) {
  const [editingId, setEditingId] = useState(null);
  const [editValues, setEditValues] = useState({ name: '', price: '' });

  const startEdit = (item) => {
    setEditingId(item.id);
    setEditValues({ name: item.name, price: item.price });
  };

  const cancelEdit = () => {
    setEditingId(null);
    setEditValues({ name: '', price: '' });
  };

  const saveEdit = () => {
    onEdit(editingId, editValues);
    cancelEdit();
  };

  return (
    <div className="item-list-vertical">
      {items.map(item => (
        <div className="flat-card" key={item.id}>
          <div className="flat-card-content">
            {editingId === item.id ? (
              <div className="edit-form">
                <input
                  type="text"
                  value={editValues.name}
                  onChange={(e) => setEditValues({ ...editValues, name: e.target.value })}
                />
                <input
                  type="number"
                  value={editValues.price}
                  onChange={(e) => setEditValues({ ...editValues, price: e.target.value })}
                />
              </div>
            ) : (
              <div>
                <h3>{item.name}</h3>
                <p>€ {item.price}</p>
              </div>
            )}

            <div className="item-actions">
              {editingId === item.id ? (
                <>
                  <button onClick={saveEdit} className="icon-button edit">
                    <FaSave />
                  </button>
                  <button onClick={cancelEdit} className="icon-button delete">
                    <FaTimes />
                  </button>
                </>
              ) : (
                <>
                  <button onClick={() => startEdit(item)} className="icon-button edit">
                    <FaEdit />
                  </button>
                  <button onClick={() => onDelete(item.id)} className="icon-button delete">
                    <FaTrash />
                  </button>
                </>
              )}
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default ItemList;
