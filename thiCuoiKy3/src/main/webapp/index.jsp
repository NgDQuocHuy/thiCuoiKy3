<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>QH Store</title>

        <jsp:include page="/layout/css_head.jsp">
            <jsp:param name="page" value="index"/>
        </jsp:include>
    </head>

    <body>

        <!-- Begin page -->
        <div id="wrapper">
            <!-- Topbar Start -->
            <div class="navbar-custom">
                <form action="/products">
                    <ul class="list-unstyled topnav-menu float-right mb-0">
                        <li class="app-search d-none d-lg-block">
                            <div class="input-group">
                                <input type="text" hint="search" name="search" value="${requestScope.search}"
                                       class="form-control" placeholder="Search...">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="get">
                                        <i class="fe-search"></i>
                                    </button>
                                </div>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>

            <!-- end Topbar -->

            <!-- ========== Left Sidebar Start ========== -->
            <jsp:include page="/layout/left_sidebar.jsp"></jsp:include>
            <!-- Left Sidebar End -->

            <!-- ============================================================== -->
            <!-- Start Page Content here -->
            <!-- ============================================================== -->

            <div class="content-page">
                <div class="content">

                    <!-- Start Content-->
                    <div class="container-fluid">

                        <!-- start page title -->
                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box">
                                    <h4 class="page-title">List Product</h4>
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->

                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <a class="header-title btn btn-primary" href="/products?action=create">Add new Product</a>

                                        <div class="table-responsive mt-3">
                                            <table class="table table-hover table-centered mb-0">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Product</th>
                                                        <th>Price</th>
                                                        <th>Quantity</th>
                                                        <th>Color</th>
                                                        <th>Info</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${requestScope['products']}" var="product">
                                                    <tr>
                                                        <td>
                                                            <p class="font-22 font-weight-light"class="font-22 font-weight-light">${product.getId()}</p>
                                                        </td>
                                                        <td>
                                                            <div class="overflow-hidden">
                                                                <p class="mb-0 font-weight-light font-24"><a href="/products?action=view&id=${product.getId()}">${product.getName()}</a></p>
                                                                <c:forEach items="${requestScope['categories']}" var="categories">
                                                                    <c:if test="${product.getIdCategory() == categories.getIdCategory()}">
                                                                        <span class="font-15">${categories.getCategory()}</span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <fmt:setLocale value = "vi_VN"/>
                                                            <p class="font-22 font-weight-light"><fmt:formatNumber value = "${product.getPrice()}" type = "currency"/></p>
                                                        </td>
                                                        <td>
                                                            <p class="font-22 font-weight-light">${product.getQuantity()}</p>
                                                        </td>
                                                        <td>
                                                            <p class="font-22 font-weight-light">${product.getColor()}</p>
                                                        </td>
                                                        <td>
                                                            <p class="font-16 font-weight-light">${product.getInfo()}</p>
                                                        </td>
                                                        <td>
                                                            <div class="btn-group dropdown">
                                                                <a href="javascript: void(0);" class="dropdown-toggle arrow-none btn btn-light btn-sm font-20" data-toggle="dropdown" aria-expanded="false"><i class="mdi mdi-dots-horizontal"></i></a>
                                                                <div class="dropdown-menu dropdown-menu-right">
                                                                    <a class="dropdown-item" href="/products?action=edit&id=${product.getId()}"><i class="mdi mdi-pencil mr-1 text-muted"></i>Edit</a>
                                                                    <a class="dropdown-item" href="/products?action=delete&id=${product.getId()}"><i class="mdi mdi-delete mr-1 text-muted"></i>Remove</a>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end row -->

                    </div> <!-- container-fluid -->

                </div> <!-- content -->

                <!-- Footer Start -->
                <footer class="footer">
                    <jsp:include page="/layout/footer_page.jsp"></jsp:include>
                </footer>
                <!-- end Footer -->
            </div>
            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->
        </div>

        <!-- END wrapper -->

        <jsp:include page="/layout/js_footer.jsp">
            <jsp:param name="page" value="index"/>
        </jsp:include>
    </body>
</html>