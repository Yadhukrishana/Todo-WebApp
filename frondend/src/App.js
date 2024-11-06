import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ProjectList from './components/ProjectList';
import ProjectDetail from './components/ProjectDetail';

function App() {
    return (
        <Router>
            <Switch>
                <Route path="/" exact component={ProjectList} />
                <Route path="/projects/:id" component={ProjectDetail} />
            </Switch>
        </Router>
    );
}

export default App;
