<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete Product</title>

    <jsp:include page="/layout/css_head.jsp">
        <jsp:param name="page" value="delete"/>
    </jsp:include>
</head>

<body>

<!-- Begin page -->
<div id="wrapper">

    <!-- Topbar Start -->
    <jsp:include page="/layout/top_nav_bar.jsp"></jsp:include>
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

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h3 class="text-primary">Delete</h3>
                                <form class="form-horizontal" method="post">
                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label" for="name">Name</label>
                                        <div class="col-lg-10">
                                            <input type="text" class="form-control" name="name" id="name" value="${product.getName()}" disabled>
                                        </div>
                                    </div>



                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label">Type Product</label>
                                        <div class="col-lg-10">
                                            <c:forEach items="${requestScope['categories']}" var="categorie">
                                                <c:if test="${product.getIdCategory() == categorie.getIdCategory()}">
                                                    <input type="text" class="form-control" name="category" id="category" value="${categorie.getCategory()}" disabled>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label">Color</label>
                                        <div class="col-lg-10">
                                            <input class="form-control" type="text" name="color" id="color" value="${product.getColor()}" disabled>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label" for="price">Price</label>
                                        <div class="col-lg-10">
                                            <input class="form-control" type="number" name="price" id="price" value="${product.getPrice()}" disabled>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label" for="quantity">Quantity</label>
                                        <div class="col-lg-10">
                                            <input class="form-control" type="number" name="quantity" id="quantity" value="${product.getQuantity()}" disabled>
                                        </div>
                                    </div>

                                    <div class="form-group row" style="margin-top: 16px">
                                        <label class="col-lg-2 col-form-label" for="info">Infomation</label>
                                        <div class="col-lg-10">
                                            <input class="form-control" rows="5" id="info" name="info" value="${product.getInfo()}" disabled>
                                        </div>
                                    </div>

                                    <div class="form-group row" style="justify-content: right">
                                        <a class="btn btn-warning col-lg-2" href="/products">Back to Home</a>
                                        <c:if test="${requestScope.message == null}">
                                            <input class="btn btn-danger col-lg-2" style="margin: 0 20px" type="submit" value="Remove">
                                        </c:if>
                                    </div>
                                </form>

                            </div>
                            <!-- end card-box -->
                        </div>
                        <!-- end card-->
                    </div>
                    <!-- end col -->
                </div>
                <!-- end row -->
            </div>
            <!-- container-fluid -->
        </div>
        <!-- content -->

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
    <jsp:param name="page" value="delete"/>
</jsp:include>

</body>
</html>