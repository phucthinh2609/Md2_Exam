package vn.mvpt.md2.exam.views;

import vn.mvpt.md2.exam.model.Product;
import vn.mvpt.md2.exam.service.IProductService;
import vn.mvpt.md2.exam.service.ProductService;
import vn.mvpt.md2.exam.utils.AppUtils;


import java.util.*;

public class ProductView {

    private final IProductService productService;
    private final Scanner scanner = new Scanner(System.in);

    public ProductView() {
        productService = ProductService.getInstance();
    }


    public void add() {
        List<Product> products = new ArrayList<>();
        do {
            long id = System.currentTimeMillis() / 1000;
            String title = inputTitle(InputOption.ADD);
            Double price = inputPrice(InputOption.ADD);
            int quantity = inputQuantity(InputOption.ADD);
            String content = inputContent(InputOption.ADD);
            Product product = new Product(id, title, price, quantity, content);
            products.add(product);
            System.out.println("Thêm sản phẩm thành công!!!");
        } while (AppUtils.isRetry(InputOption.ADD));

        boolean isConfirm = confirmProducts(products);

        if (isConfirm) {
            productService.addProducts(products);
            System.out.println("Tạo sản phẩm thành công");
        } else {
            System.out.println("Đã huỷ sản phẩm thành công ");
        }


    }

    private boolean confirmProducts(List<Product> products) {
        showConFirmProducts(products);
        System.out.println("\n+-------------------------+");
        System.out.println("|   Xác nhận đơn hàng      |");
        System.out.println("+--------------------------+");
        return AppUtils.isRetry(InputOption.CONFIRM);
    }

    private void showConFirmProducts(List<Product> products) {
        System.out.println("-----------------------------------------DANH SÁCH SẢN PHẨM-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "Giá", "Số lượng","Mô tả");
        for (Product product : products) {
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                    product.getId(),
                    product.getTitle(),
                    AppUtils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    product.getContent()

            );
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------\n");
    }

    public void delete() {
        showProducts(InputOption.DELETE);
        Long id;
        while (!productService.exist(id = inputId(InputOption.DELETE))) {
            System.err.println("Không tìm thấy sản phẩm cần xóa");
            System.out.println("Nhấn 'y' để thêm tiếp \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
            System.out.print(" ⭆ ");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    break;
            }
        }

        System.out.println("\n❄ ❄ ❄ ❄ REMOVE COMFIRM ❄ ❄ ❄");
        System.out.println("❄  1. Nhấn 1 để xoá        ❄");
        System.out.println("❄  2. Nhấn 2 để quay lại   ❄");
        System.out.println("❄ ❄ ❄ ❄ ❄ ❄ ❄  ❄ ❄ ❄ ❄ ❄ ❄ ❄");
        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            productService.deleteById(id);
            System.out.println("\nĐã xoá sản phẩm thành công! \uD83C\uDF8A");
            AppUtils.isRetry(InputOption.DELETE);
        }
    }

    public void update() {
        boolean isRetry;
        do {
            showProducts(InputOption.UPDATE);
            long id = inputId(InputOption.UPDATE);
            System.out.println("=================================================");
            System.out.println("|                                               |");
            System.out.println("|        1. Sửa mã sản phẩm                     |");
            System.out.println("|        2. Sửa tên sản phẩm                    |");
            System.out.println("|        3. Sửa giá sản phẩm                    |");
            System.out.println("|        4. Sửa số lượng sản phẩm               |");
            System.out.println("|        5. Sửa mô tả sản phẩm                  |");
            System.out.println("|                                               |");
            System.out.println("=================================================");
            System.out.println("<== 6: Quay lại                       0: Thoát");
            System.out.println("\nChọn chức năng ");

            Product newProduct = new Product();
            newProduct.setId(id);
            int choose = AppUtils.retryChoose(0, 6);
            switch (choose) {
                case 1:
//                    Long productId = inputId(InputOption.UPDATE);
//                    newProduct.setId(productId);
                    productService.update(newProduct);
                    System.out.println("Cập nhật mã sản phẩm thành công!");
                    break;
                case 2:
                    String title = inputTitle(InputOption.UPDATE);
                    newProduct.setTitle(title);
                    productService.update(newProduct);
                    System.out.println("Cập nhật tên sản phẩm thành công!");
                    break;
                case 3:
                    Double price = inputPrice(InputOption.UPDATE);
                    newProduct.setPrice(price);
                    productService.update(newProduct);
                    System.out.println("Cập nhật mô tả thành công!");
                    break;
                case 4:
                    int quantity = inputQuantity(InputOption.UPDATE);
                    newProduct.setQuantity(quantity);
                    productService.update(newProduct);
                    System.out.println("Cập nhật số lượng thành công!");
                    break;
                case 5:
                    String content = inputContent(InputOption.UPDATE);
                    newProduct.setContent(content);
                    productService.update(newProduct);
                    System.out.println("Cập nhật mô tả sản phẩm thành công!");
                    break;
                case 6:
                    ProductViewLauncher.launch();
                    break;
                case 0:
                    AppUtils.exit();
                    break;
            }
            isRetry = choose != 4 && AppUtils.isRetry(InputOption.UPDATE);
        } while (isRetry);


    }

    public void showProducts(InputOption option) {
        System.out.println("-----------------------------------------DANH SÁCH SẢN PHẨM-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "Giá", "Số lượng", "Mô tả");
        for (Product product : productService.findAll()) {
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                    product.getId(),
                    product.getTitle(),
                    AppUtils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    product.getContent()

            );
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------\n");

        if (option == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void showProductsSort(InputOption inputOption, List<Product> products) {
        System.out.println("-----------------------------------------DANH SÁCH SẢN PHẨM-------------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "Giá", "Số lượng", "Mô tả");
        for (Product product : products) {
            System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                    product.getId(),
                    product.getTitle(),
                    AppUtils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    product.getContent()

            );
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------\n");
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void showProductsFind(InputOption inputOption, Product product) {
        System.out.println("--------------------------------------DANH SÁCH ỨNG VỚI TÊN SẢN PHẨM----------------------------------------");
        System.out.printf("%-18s %-20s %-18s %-18s %-18s", "Id", "Tên sản phẩm", "Giá", "Số lượng","Mô tả");
        System.out.printf("\n%-18s %-20s %-18s %-18s %-18s",
                product.getId(),
                product.getTitle(),
                AppUtils.doubleToVND(product.getPrice()),
                product.getQuantity(),
                product.getContent()
        );

        System.out.println("\n--------------------------------------------------------------------------------------------------\n");
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void findProductByTheMostPrice() {
        List<Product> products = productService.findAll();
        Product maxProduct = products.get(0);

        for (int i = 1; i<products.size(); i++){
            if (maxProduct.getPrice() < products.get(i).getPrice()) {
                maxProduct = products.get(i);
            }
        }

        showProductsFind(InputOption.SHOW, maxProduct);
    }

    public void sortByPriceOrderByASC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByPriceASC());
    }

    public void sortByPriceOrderByDESC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByPriceDESC());
    }

    private long inputId(InputOption option) {
        Long id;
        switch (option) {
            case ADD:
                System.out.println("Nhập id: ");
                break;
            case UPDATE:
                System.out.println("Nhập id muốn sửa: ");
                break;
            case DELETE:
                System.out.println("Nhập id muốn xoá");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseLong();
            boolean exist = productService.existsById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("Id không tồn tại. Nhập lại!!!");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist)
                        System.out.println("Không tìm thấy id. Nhập lại!!!");
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;
    }

    private Double inputPrice(InputOption option) {
        Double price;
        switch (option) {
            case ADD:
                System.out.println("Nhập giá sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập giá bạn muốn sửa: ");
                break;
        }
        do {
            price = AppUtils.retryParseDouble();
            if (price <= 0) System.out.println("Giá phải lớn hơn 0 (giá > 0)");
        } while (price <= 0);
        return price;
    }

    private String inputContent(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập ghi chú sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập ghi chú sản phẩm muốn sửa: ");
                break;
        }
        System.out.print("==> ");
        return scanner.nextLine();
    }


    private String inputTitle(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập tên sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập tên sản phẩm muốn sửa: ");
                break;
            case FIND:
                System.out.println("Nhập tên sản phẩm muốn tìm");
        }
        System.out.print("⭆ ");
        String title = AppUtils.retryString();
        return title;
    }

    private int inputQuantity(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số lượng: ");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng bạn muốn sửa: ");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity <= 0) System.out.println("Số lượng phải lớn hơn 0 (giá > 0)");
        } while (quantity <= 0);
        return quantity;
    }

    public void readFile() {

    }

    public void writeFile() {

    }

    public void sortByPrice() {
        int choose;
        do {
            System.out.println("=================================================");
            System.out.println("|                                               |");
            System.out.println("|        1. Sắp xếp giá tăng dần                |");
            System.out.println("|        2. Sắp xếp giá giảm dần                |");
            System.out.println("|                                               |");
            System.out.println("=================================================");
            System.out.println("<== 3: Quay lại                       0: Thoát");
            System.out.println("\nChọn chức năng ");

            try {
                choose = AppUtils.retryChoose(0,3);
                switch (choose) {
                    case 1:
                        sortByPriceOrderByASC();
                        break;
                    case 2:
                        sortByPriceOrderByDESC();
                        break;
                    case 3:
                        ProductViewLauncher.launch();
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
}
