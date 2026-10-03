package org.sopt;

import org.sopt.post.PostController;
import org.sopt.post.PostView;

public class Main {

    public static void main(String[] args) {
        PostView view = new PostView();
        PostController controller = new PostController(view);

        while (true) {
            view.printMenu();
            int command = view.inputCommand();

            switch (command) {
                case 1:
                    controller.createPost();
                    break;

                case 2:
                    controller.checkPostList();
                    break;

                case 3:
                    controller.checkPost();
                    break;

                case 4:
                    controller.updatePost();
                    break;

                case 5:
                    controller.deletePost();
                    break;

                case 6:
                    view.printMessage("프로그램을 종료합니다.");
                    return;

                default:
                    view.printMessage("잘못된 입력입니다.");
            }
        }
    }

}
