package org.sopt.post;

import java.util.*;

public class PostController {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Post> posts = new ArrayList<>();

    public void createPost() {
        System.out.print("제목: ");
        String title = scanner.nextLine();

        System.out.print("내용: ");
        String content = scanner.nextLine();

        Post post = new Post(title, content);
        posts.add(post);

        System.out.println("게시글이 작성되었습니다.");
    }

    public void checkPostList() {
        System.out.println("\n=== 게시글 목록 ===");

        if (posts.isEmpty()) {
            System.out.println("게시글이 없습니다.");
            return;
        }

        for (int i = 0; i < posts.size(); i++) {
            Post currentPost = posts.get(i);

            System.out.println(
                    (i + 1) + ". " + currentPost.getTitle()
            );
        }
    }

    public void checkPost() {
        if (posts.isEmpty()) {
            System.out.println("게시글이 없습니다.");
            return;
        }

        System.out.print("조회할 게시글 번호: ");
        int readIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (readIndex < 0 || readIndex >= posts.size()) {
            System.out.println("존재하지 않는 게시글입니다.");
            return;
        }

        Post readPost = posts.get(readIndex);

        System.out.println("\n=== 게시글 ===");
        System.out.println("제목: " + readPost.getTitle());
        System.out.println("내용: " + readPost.getContent());
    }

    public void updatePost() {
        if (posts.isEmpty()) {
            System.out.println("게시글이 없습니다.");
            return;
        }

        System.out.print("수정할 게시글 번호: ");
        int updateIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (updateIndex < 0 || updateIndex >= posts.size()) {
            System.out.println("존재하지 않는 게시글입니다.");
            return;
        }

        Post updatePost = posts.get(updateIndex);

        System.out.print("새로운 제목: ");
        String newTitle = scanner.nextLine();

        System.out.print("새로운 내용: ");
        String newContent = scanner.nextLine();

        updatePost.updateTitle(newTitle);
        updatePost.updateContent(newContent);

        System.out.println("게시글이 수정되었습니다.");
    }

    public void deletePost() {
        if (posts.isEmpty()) {
            System.out.println("게시글이 없습니다.");
            return;
        }

        System.out.print("삭제할 게시글 번호: ");
        int deleteIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (deleteIndex < 0 || deleteIndex >= posts.size()) {
            System.out.println("존재하지 않는 게시글입니다.");
            return;
        }

        posts.remove(deleteIndex);

        System.out.println("게시글이 삭제되었습니다.");
    }

    public String wrongInput() {
        return("잘못된 입력입니다.");
    }

}
