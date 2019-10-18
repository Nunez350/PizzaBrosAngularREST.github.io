package com.PizzaBros.PizzaBroRESTBackend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PizzaBros.PizzaBroRESTBackend.DTO.ProductDTO;
import com.PizzaBros.PizzaBroRESTBackend.mapper.ProductMapper;
import com.PizzaBros.PizzaBroRESTBackend.model.Product;
import com.PizzaBros.PizzaBroRESTBackend.repository.ProductRepository;



@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductMapper productMapper;
	
	
	public List<ProductDTO> findAll(String category) {
		List<Product> findAll = null;
		
		if (category != null && !category.isEmpty()) {
			findAll = productRepository.findAllByCategory(category);
		} else {
			findAll = productRepository.findAll();
		}
		return findAll.stream().map(p -> productMapper.toDto(p)).collect(Collectors.toList());
	}
	
	
	
	public List<ProductDTO> findCategory(String category) {
		List<Product> findAll = null;
			findAll = productRepository.findAllByCategory(category);
		return findAll.stream().map(p -> productMapper.toDto(p)).collect(Collectors.toList());
	}
	

	
	public ProductDTO findOne(Long id) {
		Optional<Product> productOp = productRepository.findById(id);
		if (productOp.isPresent()) {
			return productMapper.toDto(productOp.get());
		}
		return null;
	}
	
	

	
	public ProductDTO save(ProductDTO product) {
		Product entity = productMapper.toEntity(product);
		Product saved = productRepository.save(entity);
	return productMapper.toDto(saved);
	}
	
	
	public ProductDTO update(ProductDTO product, Long id) {
		Optional<Product>findById = productRepository.findById(id);
		if (findById.isPresent()) {
			Product p = findById.get();
			p.setName(product.getName());
			p.setDescription(product.getDescription());
			p.setImage(product.getImage());
			p.setPrice(product.getPrice());
			p.setInventory(product.getInventory());
			p.setSales(product.getSales());
			
			ProductDTO productDTO = new ProductDTO();
			//Pizza saved=pizzaDTO.save(p);
			Product saved = productRepository.save(p);
			return productMapper.toDto(saved);
		} else {
			throw new IllegalArgumentException();
			}
		}
	
	public void delete(Long id) {
		productRepository.deleteById(id);
	}


	public ProductDTO findAllProducts(String category) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
