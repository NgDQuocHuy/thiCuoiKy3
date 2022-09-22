<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Product</title>

    <jsp:include page="/layout/css_head.jsp">
        <jsp:param name="page" value="edit"/>
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

                <!-- start page title -->
                <div class="row" style="margin-top: 20px">
                    <div class="col-12">
                        <div class="page-title-box" style="margin-bottom: 20px">
                            <a class="btn btn-warning" href="/products">Back to Home</a>
                        </div>
                    </div>
                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h3 class="text-primary">Edit</h3>
                                <c:if test="${requestScope.errors != null}">
                                    <div class="alert alert-icon alert-danger alert-dismissible fade show mb-0"
                                         role="alert">
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                        <strong>Errors!</strong> <br>
                                        <c:forEach items="${requestScope.errors}" var="item">
                                            <li>${item}</li>
                                        </c:forEach>
                                    </div>
                                </c:if>

                                <form class="form-horizontal" method="post">
                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label" for="name">Name</label>
                                        <div class="col-lg-10">
                                            <input type="text" class="form-control" name="name" id="name" value="${requestScope['product'].getName()}" placeholder="Input name..."/>
                                        </div>
                                    </div>



                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label">Category Product</label>
                                        <div class="col-lg-10">
                                            <select class="form-control" name="category" id="category">
                                                <c:forEach items="${requestScope['categories']}" var="categorie">
                                                    <c:choose>
                                                        <c:when test="${categorie.getIdCategory() == requestScope['product'].getIdCategory()}">
                                                            <option selected value="${categorie.getIdCategory()}">${categorie.getCategory()}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${categorie.getIdCategory()}">${categorie.getCategory()}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label" for="price">Price</label>
                                        <div class="col-lg-10">
                                            <input class="form-control" type="number" name="price" id="price" value="${requestScope['product'].getPrice()}" placeholder="Input price..."/>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label" for="quantity">Quantity</label>
                                        <div class="col-lg-10">
                                            <input class="form-control" type="number" name="quantity" id="quantity" value="${requestScope['product'].getQuantity()}" placeholder="Input quantity..."/>
                                        </div>
                                    </div>

                                    <div class="form-group row mb-0">
                                        <label class="col-lg-2 col-form-label">Color</label>
                                        <div class="col-lg-10">
                                            <input class="form-control" type="text" name="color" id="color" value="${requestScope['product'].getColor()}" placeholder="Input url Color..."/>
                                        </div>
                                    </div>

                                    <div class="form-group row" style="margin-top: 16px">
                                        <label class="col-lg-2 col-form-label" for="info">Infomation</label>
                                        <div class="col-lg-10">
                                            <input class="form-control" rows="5" id="info" name="info" value="${requestScope['product'].getInfo()}" placeholder="Information Product..."/>
                                        </div>
                                    </div>

                                    <div class="form-group row" style="justify-content: right">
                                        <a class="btn btn-warning col-lg-2" href="/products">Back to Home</a>
                                        <input class="btn btn-primary col-lg-2" style="margin: 0 20px" type="submit" value="Save">
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
<c:if test="${requestScope.messagee != null}">
    <script>
        window.onload = function(e){
            <c:set var = "message" scope = "session" value = "${requestScope.messagee}"/>
            toastr["warning"]("${messagee}");
        }
    </script>
</c:if>
<c:if test="${requestScope.message != null}">
    <script>
        window.onload = function(e){
            <c:set var = "message" scope = "session" value = "${requestScope.message}"/>
            toastr["info"]("${message}");
        }
    </script>
</c:if>
<!-- END wrapper -->

<script>
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": true,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
</script>

<jsp:include page="/layout/js_footer.jsp">
    <jsp:param name="page" value="edit"/>
</jsp:include>

</body>
</html>