package org.sopt.post.view;

import org.sopt.post.domain.Category;
import org.sopt.post.domain.Post;

import java.util.List;
import java.util.Scanner;

public class PostView {
    private final Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        System.out.println("\n=== 게시판 ===");
        System.out.println("1. 게시글 작성");
        System.out.println("2. 게시글 목록 조회");
        System.out.println("3. 게시글 단건 조회");
        System.out.println("4. 게시글 수정");
        System.out.println("5. 게시글 삭제");
        System.out.println("6. 종료");
    }

    public int inputCommand() {
        System.out.print("선택: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String inputTitle() {
        System.out.print("제목: ");
        return scanner.nextLine();
    }

    public String inputContent() {
        System.out.print("내용: ");
        return scanner.nextLine();
    }

    public String inputNewTitle() {
        System.out.print("새로운 제목: ");
        return scanner.nextLine();
    }

    public String inputAuthor() {
        System.out.print("작성자: ");
        return scanner.nextLine();
    }

    public String inputNewContent() {
        System.out.print("새로운 내용: ");
        return scanner.nextLine();
    }

    public Long inputIndex(String message) {
        System.out.print(message);
        return Long.parseLong(scanner.nextLine());
    }

    public Category inputCategory() {
        for(int i = 0; i < Category.values().length; i++) {
            System.out.println((i + 1) + ". " + Category.values()[i].getLabel());
        }
        System.out.print("카테고리 선택 : ");
        int number = Integer.parseInt(scanner.nextLine());
        if(number < 1 || number > Category.values().length) {
            throw new IllegalArgumentException("올바른 카테고리 번호를 입력해주세요.");
        }
        return Category.values()[number - 1];
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printPostList(List<Post> posts) {
        System.out.println("\n=== 게시글 목록 ===");

        for (int i = 0; i < posts.size(); i++) {
            System.out.println(posts.get(i).getId() + ". " + posts.get(i).getTitle());
        }
    }

    public void printPost(Post post) {
        System.out.println("\n=== 게시글 ===");
        System.out.println("제목: " + post.getTitle());
        System.out.println("콘텐츠: " + post.getContent());
        System.out.println("카테고리: " + post.getCategory().getLabel());
        System.out.println("작성자: " + post.getAuthor());
        System.out.println("작성일: " + post.getCreatedAt());
    }
}
