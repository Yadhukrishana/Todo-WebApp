import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

function ProjectDetail() {
    const { id } = useParams();
    const [project, setProject] = useState(null);
    const [newTodo, setNewTodo] = useState("");

    useEffect(() => {
        axios.get(`/api/projects/${id}`)
            .then(response => setProject(response.data))
            .catch(error => console.error("There was an error fetching the project!", error));
    }, [id]);

    const handleAddTodo = () => {
        axios.post(`/api/projects/${id}/todos`, { description: newTodo, completed: false })
            .then(response => setProject(prev => ({
                ...prev,
                todos: [...prev.todos, response.data]
            })))
            .catch(error => console.error("There was an error adding the todo!", error));
        setNewTodo("");
    };

    const handleToggleTodo = (todoId, completed) => {
        axios.put(`/api/projects/todos/${todoId}`, { completed: !completed })
            .then(response => {
                setProject(prev => ({
                    ...prev,
                    todos: prev.todos.map(todo => todo.id === todoId ? response.data : todo)
                }));
            })
            .catch(error => console.error("There was an error updating the todo!", error));
    };

    const handleDeleteTodo = (todoId) => {
        axios.delete(`/api/projects/${id}/todos/${todoId}`)
            .then(() => setProject(prev => ({
                ...prev,
                todos: prev.todos.filter(todo => todo.id !== todoId)
            })))
            .catch(error => console.error("There was an error deleting the todo!", error));
    };

    return (
        <div>
            {project && (
                <>
                    <h2>{project.title}</h2>
                    <h3>Pending Todos</h3>
                    <ul>
                        {project.todos.filter(todo => !todo.completed).map(todo => (
                            <li key={todo.id}>
                                <input type="checkbox" checked={todo.completed} onChange={() => handleToggleTodo(todo.id, todo.completed)} />
                                {todo.description}
                                <button onClick={() => handleDeleteTodo(todo.id)}>Delete</button>
                            </li>
                        ))}
                    </ul>

                    <h3>Completed Todos</h3>
                    <ul>
                        {project.todos.filter(todo => todo.completed).map(todo => (
                            <li key={todo.id}>
                                <input type="checkbox" checked={todo.completed} onChange={() => handleToggleTodo(todo.id, todo.completed)} />
                                {todo.description}
                                <button onClick={() => handleDeleteTodo(todo.id)}>Delete</button>
                            </li>
                        ))}
                    </ul>

                    <input value={newTodo} onChange={(e) => setNewTodo(e.target.value)} placeholder="New Todo" />
                    <button onClick={handleAddTodo}>Add Todo</button>

                    <button onClick={() => {
                        axios.get(`/api/projects/${id}/export`).then(response => {
                            const blob = new Blob([response.data], { type: 'text/markdown' });
                            const url = URL.createObjectURL(blob);
                            const a = document.createElement('a');
                            a.href = url;
                            a.download = `${project.title}.md`;
                            a.click();
                        });
                    }}>Export to Markdown</button>
                </>
            )}
        </div>
    );
}

export default ProjectDetail;
