import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Table, Divider, Form, Input, Button, message, Modal, Popconfirm } from 'antd';
import { request } from '../../common';
import { tokenKey } from '../../config';
import AdminLayout from '../../components/AdminLayout';
const FormItem = Form.Item;

function UpdateModal(props) {

    const { getFieldDecorator, validateFields, resetFields } = props.form;

    function handleClick(e) {
        e.preventDefault();
        validateFields((errors, values) => {
            if (errors) {
                return;
            }
            const data = {
                ...props.tag,
                name: values.name
            }
            props.handleUpdateTag(data);
        });
        resetFields();
    }

    return (
        <div>
            <Modal
                title="Update Tag"
                visible={props.visible}
                onOk={handleClick}
                onCancel={props.handleCloseModal}
            >
                <Form>
                    <FormItem label="name">
                        {getFieldDecorator('name', {
                            initialValue: props.tag.name,
                            rules: [{ required: true, message: "please input name" }],
                        })(<Input />)}
                    </FormItem>
                </Form>
            </Modal>
        </div>
    );
}

UpdateModal = Form.create()(UpdateModal);

class AdminTagListPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            updateModalOpts: {
                tag: {},
                visible: false,
            },
            tags: [],
        };
        this.fetchTags = this.fetchTags.bind(this);
        this.handleAddTag = this.handleAddTag.bind(this);
        this.handleCloseModal = this.handleCloseModal.bind(this);
        this.handleUpdateTag = this.handleUpdateTag.bind(this);
        this.handleDeleteTag = this.handleDeleteTag.bind(this);
    }

    fetchTags() {
        const token = window.localStorage.getItem(tokenKey);
        request(`/api/admin/config/tags`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${token}`
            },
        })
            .then(data => this.setState({
                tags: data,
            }));
    }

    handleAddTag() {
        const { validateFields, resetFields } = this.props.form;
        validateFields((errors, values) => {
            if (!errors) {
                const data = {
                    code: values.code,
                    name: values.name
                }
                const token = window.localStorage.getItem(tokenKey);
                request(`/api/admin/config/tags`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        "Authorization": `Bearer ${token}`
                    },
                    body: JSON.stringify(data)
                }).then((response) => {
                    this.fetchTags();
                    resetFields();
                }).catch((error) => {
                    message.error('submit failed');
                });
            }
        });
    }

    handleUpdateTag(tag) {
        const token = window.localStorage.getItem(tokenKey);
        request(`/api/admin/config/tags/${tag.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(tag)
        }).then((response) => {
            this.handleCloseModal();
            this.fetchTags();
        }).catch((error) => {
            message.error('submit failed');
        });
    }

    handleDeleteTag(id) {
        const token = window.localStorage.getItem(tokenKey);
        request(`/api/admin/config/tags/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${token}`
            },
        }).then((response) => {
            this.fetchTags();
        }).catch((error) => {
            message.error('submit failed');
        });
    }

    handleCloseModal() {
        this.setState({
            updateModalOpts: {
                tag: {},
                visible: false,
            }
        });
    }

    componentDidMount() {
        this.fetchTags();
    }

    render() {

        const columns = [{
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
        }, {
            title: 'Action',
            key: 'action',
            render: (text, record) => (
                <span>
                    <a onClick={() => this.setState({ updateModalOpts: { visible: true, tag: record } })}>Rename</a>
                    <Divider type="vertical" />
                    <Popconfirm title={`delete tag : ${record.name} ?`} onConfirm={() => this.handleDeleteTag(record.id)} okText="Yes" cancelText="No">
                        <a>Delete</a>
                    </Popconfirm>
                </span>
            )
        }];

        const { getFieldDecorator } = this.props.form;

        const updateModalOpts = {
            ...this.state.updateModalOpts,
            handleUpdateTag: this.handleUpdateTag,
            handleCloseModal: this.handleCloseModal,
        }

        return (
            <div>
                <AdminLayout>
                    <h1>AdminTagListPage</h1>
                    <Form layout="inline" onSubmit={this.handleAddTag}>
                        <FormItem label="code">
                            {getFieldDecorator('code', {
                                initialValue: "",
                                rules: [{ required: true, message: "please input code" }],
                            })(<Input />)}
                        </FormItem>
                        <FormItem label="name">
                            {getFieldDecorator('name', {
                                initialValue: "",
                                rules: [{ required: true, message: "please input name" }],
                            })(<Input />)}
                        </FormItem>
                        <FormItem>
                            <Button type="primary" htmlType="submit">Add</Button>
                        </FormItem>
                    </Form>
                    <Table columns={columns} dataSource={this.state.tags} />
                </AdminLayout>
                <UpdateModal {...updateModalOpts} />
            </div>
        );
    }
}

AdminTagListPage = Form.create()(AdminTagListPage);

export default AdminTagListPage;