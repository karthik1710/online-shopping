<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<div class="row">

		<c:if test="${not empty message}">

			<c:choose>

				<c:when test="${message eq 'Product Submission Successfull!'}">
					<div class="col-xs-12">
						<div class="alert alert-success alert-dismissible">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${message}
						</div>
					</div>
				</c:when>

				<c:otherwise>
					<div class="col-xs-12">
						<div class="alert alert-success alert-dismissible">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${message}
						</div>
					</div>
				</c:otherwise>
			</c:choose>

		</c:if>


		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Product Management</h4>

				</div>

				<div class="panel-body">

					<!-- Form Elements -->
					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">

						<div class="form-group">

							<label class="control-label col-md-4" for="name"> Product
								Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name"
									placeholder="Product Name" class="form-control" />
								<sf:errors path="name" cssClass="help-block" element="em" />

							</div>


						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="brand"> Brand
								Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="description">Description:
							</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description"
									placeholder="Write a description" rows="4" class="form-control" />
								<sf:errors path="description" cssClass="help-block" element="em" />
							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="brand"> Unit
								Price: </label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice"
									placeholder="Unit Price in Rs" class="form-control" />
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />
							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="brand">Quantity
								Available: </label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity"
									placeholder="Quantity Available" class="form-control" />
							</div>

						</div>

						<!-- File element for a image upload -->
						<div class="form-group">

							<label class="control-label col-md-4" for="file"> Choose
								a Product Image: </label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>

						</div>


						<div class="form-group">

							<label class="control-label col-md-4" for="brand"> Select
								Category: </label>
							<div class="col-md-8">
								<sf:select class="form-control" path="categoryId" id="categoryId" 
									items="${categories}"
									itemLabel="name"
									itemValue="id" />

								<c:if test="${product.id == 0}">
									<div class="text-right">
										<br />
										<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add Category</button>
									</div>
								</c:if>
							</div>

						</div>




						<div class="form-group">


							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" Value="Submit"
									class="btn btn-primary" />
								<!-- Hidden fields for Product -->
								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="active" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />

							</div>

						</div>

					</sf:form>

				</div>

			</div>

		</div>
	</div>

	<div class="row">

		<div class="col-xs-12">
			<h3>Products Available</h3>
		</div>
		<div class="col-xs-12">
			<div style="overflow: auto">
				<div class="container-fluid">
					<div class="table-responsive">
						<!-- Products table for admin -->
						<table id="adminProductsTable"
							class="table table-striped table-bordered">
							<thead>
								<tr>

									<td>ID</td>
									<td>&#160;</td>
									<td>Name</td>
									<td>Brand</td>
									<td>Quantity</td>
									<td>Unit Price</td>
									<td>Active</td>
									<td>Edit</td>
								</tr>
							</thead>

							<tfoot>
								<tr>

									<td>ID</td>
									<td>&#160;</td>
									<td>Name</td>
									<td>Brand</td>
									<td>Quantity</td>
									<td>Unit Price</td>
									<td>Active</td>
									<td>Edit</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="modal fade" id="myCategoryModal" role="dialog"
		tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title">New Category</h4>
				</div>
				<div class="modal-body">
					<!-- Category Form -->
					<sf:form id="categoryForm" class="form-horizontal" modelAttribute="category"
						action="${contextRoot}/manage/category" 
						method="POST">


						<div class="form-group">
							<label class="control-label col-md-4" for="category_name"> Category Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="category_name" placeholder="Category Name" class="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="category_description">Category Description:</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="category_description" placeholder="Write a description" rows="4" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" Value="Add Category" class="btn btn-primary" />
							</div>
						</div>			
					</sf:form>

				</div>
			</div>
		</div>
	</div>

</div>
