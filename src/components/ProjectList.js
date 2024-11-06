import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function ProjectList() {
    const [projects, setProjects] = useState([]);

    useEffect(() => {
        axios.get('/api/projects')
            .then(response => setProjects(response.data))
            .catch(error => console.error("There was an error fetching the projects!", error));
    }, []);

    return (
        <div>
            <h1>Projects</h1>
            <Link to="/new">Create New Project</Link>
            <ul>
                {projects.map(project => (
                    <li key={project.id}>
                        <Link to={`/projects/${project.id}`}>{project.title}</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default ProjectList;
