package vn.mvpt.md2.exam.views;


import vn.mvpt.md2.exam.utils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductViewLauncher {

    public static void launch() {
        int choose;
        do {
            menuProduct();
            ProductView productView = new ProductView();

            try {
                choose = AppUtils.retryChoose(0,8);
                switch (choose) {
                    case 1:
                        productView.showProducts(InputOption.SHOW);
                        break;
                    case 2:
                        productView.add();
                        break;
                    case 3:
                        productView.update();

                        break;
                    case 4:
                        productView.delete();

                        break;
                    case 5:
                        productView.sortByPrice();

                        break;
                    case 6:
                        productView.findProductByTheMostPrice();
                        break;
                    case 7:
                        productView.readFile();
                        break;
                    case 8:
                        productView.writeFile();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.err.println("Chọn sai. Chọn lại!!!");
                }

            } catch (InputMismatchException ex) {
                System.err.println("Nhập sai. Nhập lại!!!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public static void menuProduct() {
        System.out.println("\n=================================================");
        System.out.println("|       CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM           |");
        System.out.println("=================================================");
        System.out.println("|                                               |");
        System.out.println("|        1. Xem danh sách                       |");
        System.out.println("|        2. Thêm mới                            |");
        System.out.println("|        3. Cập nhật                            |");
        System.out.println("|        4. Xoá theo id                         |");
        System.out.println("|        5. Sắp xếp                             |");
        System.out.println("|        6. Tìm sản phẩm đắt giá nhất           |");
        System.out.println("|        7. Đọc vào từ file                     |");
        System.out.println("|        8. Ghi vào từ file                     |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println(" 0: Thoát");
        System.out.println("\nChọn chức năng ");
    }
}
