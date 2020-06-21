import { tokenKey } from './config';
import moment from 'moment';

export const auth = {
    isAdmin() {
        const token = window.localStorage.getItem(tokenKey);
        if (token) {
            return true;
        }
        else {
            return false;
        }
    },
    getToken() {
        return window.localStorage.getItem(tokenKey);
    }
}

export function request(url, options) {
    return fetch(url, options)
        .then(function (response) {
            if (response.ok) {
                return response.json();
            }
            else {
                if (response.status === 401) {
                    window.localStorage.removeItem(tokenKey);
                    window.location = '/login';
                }
                else {
                    console.log('something wrong');
                }
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log('get a problem: ' + error.message);
        });
}

export function time2DateStr(time) {
    return moment.unix(time / 1000).format('YYYY-MM-DD');
}
