package kodlamaio.northwind.business.concrates;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concrates.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;



    @Autowired
    ProductManager(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public DataResult<List<Product>> getAll() {

        return
                new SuccessDataResult<List<Product>>
                        (this.productDao.findAll(), "Data Listelendi");

    }

    @Override
    public DataResult<List<Product>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC,"productName");
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort),"Başarılı");
    }

    @Override
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent());
    }

    @Override
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessResult("Ürün Eklendi");
    }

    @Override
    public DataResult<Product> getByProductName(String productName) {
        return
                new SuccessDataResult<Product>
                        (this.productDao.getByProductName(productName), "Data Listelendi");
    }

    @Override
    public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
        return
                new SuccessDataResult<Product>
                        (this.productDao.getByProductNameAndCategory_CategoryId(productName,categoryId), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameOrCategory(String productName, int categoryId) {
        return
                new SuccessDataResult<List<Product>>
                        (this.productDao.getByProductNameOrCategory_CategoryId(productName,categoryId), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByCategoryId(List<Integer> categories) {
        return
                new SuccessDataResult<List<Product>>
                        (this.productDao.getByCategory_CategoryIdIn(categories), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameContains(String productName) {
        return
                new SuccessDataResult<List<Product>>
                        (this.productDao.getByProductNameContains(productName), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
        return
                new SuccessDataResult<List<Product>>
                        (this.productDao.getByProductNameStartsWith(productName), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
        return
                new SuccessDataResult<List<Product>>
                        (this.productDao.getByNameAndCategory(productName,categoryId), "Data Listelendi");
    }

    @Override
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
        return new SuccessDataResult<List<ProductWithCategoryDto>>(this.productDao.getProductWithCategoryDetails(),"Data Listelendi");
    }
}
