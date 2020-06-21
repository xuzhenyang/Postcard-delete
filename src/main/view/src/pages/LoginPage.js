import React, { Component } from 'react';
import { tokenKey } from '../config';

class LoginPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            account: '',
            password: ''
        };
        this.handleAccountChange = this.handleAccountChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleAccountChange(event) {
        this.setState({
            account: event.target.value
        });
    }

    handlePasswordChange(event) {
        this.setState({
            password: event.target.value
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        let request = {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "account": this.state.account,
                "password": this.state.password
            })
        }
        fetch('/auth/login', request)
            .then(response => response.json())
            .then(response => {
                if (response.token) {
                    const token = response.token;
                    window.localStorage.setItem(tokenKey, token);
                    this.props.history.push('/admin');
                }
                else {
                    alert('wrong account or password')
                }
            })
    }

    render() {
        return (
            <div>
                LoginPage
                <form>
                    <label>
                        account:
                        <input type="text" value={this.state.account} onChange={this.handleAccountChange} />
                    </label>
                    <label>
                        password:
                        <input type="password" value={this.state.password} onChange={this.handlePasswordChange} />
                    </label>
                    <input type="submit" value="Submit" onClick={this.handleSubmit} />
                </form>
            </div>
        );
    }
}

export default LoginPage;