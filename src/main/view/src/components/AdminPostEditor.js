import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Form, Input, Button, Select, Tabs } from 'antd';
import { request } from '../common';
import { tokenKey } from '../config';
import marked3 from 'marked3';
import hljs from 'highlight.js';
import '../rainbow.css';
import './AdminPostEditor.css';
const FormItem = Form.Item;
const { TextArea } = Input;
const TabPane = Tabs.TabPane;
const Option = Select.Option;

class AdminPostEditor extends Component {
    constructor(props) {
        super(props);
        this.state = {
            post: props.post,
            tags: [],
        };
        this.onContentChange = this.onContentChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    fetchTags() {
        const token = window.localStorage.getItem(tokenKey);
        request(`/api/admin/config/tags`, {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            },
        })
            .then(data => this.setState({
                tags: data,
            }));
    }

    onContentChange(e) {
        this.setState({
            post: {
                content: e.target.value
            }
        })
    }

    onSubmit(e) {
        e.preventDefault();
        const { validateFields, resetFields } = this.props.form;
        validateFields((errors, values) => {
            if (errors) {
                return;
            }
            const data = {
                id: this.props.post.id,
                title: values.title,
                tags: values.tags,
                content: values.content,
            };
            //tags转换
            const { tags } = data;
            let tagList = [];
            for (let index in tags) {
                tagList.push({
                    code: tags[index].key,
                    name: tags[index].label
                });
            }
            let result = {
                id: data.id,
                title: data.title,
                tagParamList: tagList,
                content: data.content
            };
            this.props.handleSubmit(result);
        });
        resetFields();
    }

    componentDidMount() {
        this.fetchTags();
    }

    render() {

        const {
            post,
        } = this.props;

        const { getFieldDecorator } = this.props.form;

        const formItemLayout = {
            labelCol: { span: 2 },
            wrapperCol: { span: 8 }
        }

        const options = [];
        const tags = this.state.tags;
        for (let index in tags) {
            options.push(<Option key={tags[index].code}>{tags[index].name}</Option>);
        }

        const defaultSelected = [];
        if (post && Array.isArray(post.tagList) && post.tagList.length > 0) {
            for (let index in post.tagList) {
                defaultSelected.push({
                    key: post.tagList[index].code,
                    label: post.tagList[index].name
                });
            }
        }

        return (
            <div>
                <Form>
                    <FormItem label="Title" {...formItemLayout}>
                        {getFieldDecorator('title', {
                            initialValue: post.title ? post.title : '',
                            rules: [{ required: true, message: 'please input title !' }]
                        })(
                            <Input />
                            )}
                    </FormItem>
                    <FormItem label="Tags" {...formItemLayout}>
                        {getFieldDecorator('tags', {
                            initialValue: defaultSelected,
                        })(
                            <Select
                                mode="multiple"
                                labelInValue
                            >
                                {options}
                            </Select>
                            )}
                    </FormItem>
                    <Tabs defaultActiveKey="1">
                        <TabPane tab="edit" key="1">
                            <FormItem>
                                {getFieldDecorator('content', {
                                    initialValue: post.content ? post.content : '',
                                    rules: [{ required: true, message: 'please input content !' }]
                                })(
                                    <TextArea rows={20} onChange={this.onContentChange} />
                                    )}
                            </FormItem>
                        </TabPane>
                        <TabPane tab="preview" key="2">
                            <div className="preview" style={{ height: 420, overflowY: "scroll", marginBottom: 24, border: "1px solid #d9d9d9", borderRadius: 4, padding: 10 }} dangerouslySetInnerHTML={{
                                __html: marked3(this.state.post.title === "" ? post.content : this.state.post.content, {
                                    highlight: (code => hljs.highlightAuto(code).value)
                                })
                            }} />
                        </TabPane>
                    </Tabs>
                    <Button onClick={this.onSubmit}>Submit</Button>
                </Form>
            </div>
        );
    }
}

AdminPostEditor.propTypes = {
    post: PropTypes.shape({
        title: PropTypes.string,
        tags: PropTypes.array,
        content: PropTypes.string,
    }),
    handleSubmit: PropTypes.func,
}

AdminPostEditor.defaultProps = {
    post: {
        title: '',
        tags: [],
        content: '',
    }
};

AdminPostEditor = Form.create({})(AdminPostEditor);

export default AdminPostEditor