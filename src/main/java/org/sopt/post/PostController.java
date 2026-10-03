package org.sopt.post;

import java.util.ArrayList;
import java.util.List;

public class PostController {
    private final List<Post> posts = new ArrayList<>();
    private final PostView view;

    public PostController(PostView view) {
        this.view = view;
    }

    public void createPost() {
        String title = view.inputTitle();
        String content = view.inputContent();

        Post post = new Post(title, content);
        posts.add(post);

        view.printMessage("게시글이 작성되었습니다.");
    }

    public void checkPostList() {
        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }

        view.printPostList(posts);
    }

    public void checkPost() {
        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }

        int readIndex = view.inputIndex("조회할 게시글 번호: ");

        if (readIndex < 0 || readIndex >= posts.size()) {
            view.printMessage("존재하지 않는 게시글입니다.");
            return;
        }

        view.printPost(posts.get(readIndex));
    }

    public void updatePost() {
        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }

        int updateIndex = view.inputIndex("수정할 게시글 번호: ");

        if (updateIndex < 0 || updateIndex >= posts.size()) {
            view.printMessage("존재하지 않는 게시글입니다.");
            return;
        }

        Post updatePost = posts.get(updateIndex);

        String newTitle = view.inputNewTitle();
        String newContent = view.inputNewContent();

        updatePost.updateTitle(newTitle);
        updatePost.updateContent(newContent);

        view.printMessage("게시글이 수정되었습니다.");
    }

    public void deletePost() {
        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }

        int deleteIndex = view.inputIndex("삭제할 게시글 번호: ");

        if (deleteIndex < 0 || deleteIndex >= posts.size()) {
            view.printMessage("존재하지 않는 게시글입니다.");
            return;
        }

        posts.remove(deleteIndex);

        view.printMessage("게시글이 삭제되었습니다.");
    }
}
