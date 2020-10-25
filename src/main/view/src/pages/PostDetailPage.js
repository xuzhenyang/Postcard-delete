import React, { Component } from 'react';
import marked3 from 'marked3';
import hljs from 'highlight.js';
import '../rainbow.css';
import './PostDetailPage.css';
import { time2DateStr } from '../common';

function Post(post) {
    if (post && post.id) {
        const tagList = [];
        let tags = post.tagList;
        if (Array.isArray(tags) && tags.length > 0)
        tags.forEach(tag => {
            tagList.push(<span style={{"margin": 5}}>{tag.name}</span>);
        });
        return (
            <div className="main">
                <h1>{post.title}</h1>
                <p>{tagList}</p>
                <p>{time2DateStr(post.dateCreate)}</p>
                <div className="container">
                    <div dangerouslySetInnerHTML={{
                        __html: marked3(post.content, {
                            highlight: (code => hljs.highlightAuto(code).value)
                        })
                    }} />
                </div>
            </div>
        );
    }
    return (
        <div>
            loading...
        </div>
    );
}

class PostDetailPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            post: {},
        };
    }

    componentDidMount() {
        const api = '/api/posts/' + this.props.match.params.id;
        fetch(api)
            .then(res => res.json())
            .then(res => this.setState({
                post: res
            }));
    }

    render() {
        return (
            Post(this.state.post)
        );
    }
}

export default PostDetailPage;