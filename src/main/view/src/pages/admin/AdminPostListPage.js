import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Table, Divider, Switch } from 'antd';
import { request } from '../../common';
import { tokenKey } from '../../config';
import AdminLayout from '../../components/AdminLayout';

class AdminPostListPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            pagination: {},
        };
        this.fetchPosts = this.fetchPosts.bind(this);
        this.handleTableChange = this.handleTableChange.bind(this);
        this.handleSwitch = this.handleSwitch.bind(this);
    }

    fetchPosts(page, pageSize) {
        page = page ? page : 1;
        pageSize = pageSize ? pageSize : 10;
        const token = window.localStorage.getItem(tokenKey);
        request(`/api/admin/posts?page=${page}&pageSize=${pageSize}`, {
            method: 'GET',
            headers: new Headers({
                "Authorization": `Bearer ${token}`
            })
        })
            .then(data => {
                if (data != undefined) {
                    this.setState({
                        posts: data.data,
                        pagination: {
                            current: data.page,
                            total: data.total,
                        }
                    })
                }
            });
    }

    componentDidMount() {
        this.fetchPosts(1, 10);
    }

    handleTableChange(pagination, filters, sorter) {
        this.fetchPosts(pagination.current, pagination.pageSize);
    }

    handleSwitch(id, checked) {
        console.log('id: ', id);
        console.log(`switch to ${checked}`);
        const token = window.localStorage.getItem(tokenKey);
        if (checked) {
            request(`/api/admin/posts/${id}/publish`, {
                method: 'PUT',
                headers: new Headers({
                    "Authorization": `Bearer ${token}`
                })
            })
            .then(this.fetchPosts());
        }
        else {
            request(`/api/admin/posts/${id}/withdraw`, {
                method: 'PUT',
                headers: new Headers({
                    "Authorization": `Bearer ${token}`
                })
            })
            .then(this.fetchPosts());
        }
    }

    render() {
        const columns = [{
            title: 'Title',
            dataIndex: 'title',
            key: 'title',
        }, {
            title: 'Action',
            key: 'action',
            render: (text, record) => (
                <span>
                    <a href={`/admin/posts/edit/${record.id}`}>Edit</a>
                    <Divider type="vertical" />
                    <Switch checkedChildren="已发布" unCheckedChildren="草稿"
                        defaultChecked={record.status === 2} onChange={(checked) => this.handleSwitch(record.id, checked)} />
                </span>
            )
        }]

        return (
            <div>
                <AdminLayout>
                    <h1>AdminPostListPage</h1>
                    <Link to="/admin/posts/new">
                        <button>new</button>
                    </Link>
                    <Table columns={columns} dataSource={this.state.posts} pagination={this.state.pagination} onChange={this.handleTableChange} />
                </AdminLayout>
            </div>
        );
    }
}

export default AdminPostListPage;