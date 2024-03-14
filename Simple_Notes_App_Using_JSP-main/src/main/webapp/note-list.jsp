<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> -->
<html>
  <head>
    <title>Note Application</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <header>
      <nav
        class="navbar navbar-expand-md navbar-dark"
        style="background-color: rgb(18, 49, 150)"
      >
        <div>
          <a href="/" class="navbar-brand">
            Note App
          </a>
        </div>

        <ul class="navbar-nav">
          <li>
            <a href="<%=request.getContextPath()%>/list" class="nav-link"
              >Notes</a
            >
          </li>
        </ul>
      </nav>
    </header>
    <br />

    <div class="row">
      <div class="container">
        <h3 class="text-center">List of Notes</h3>
        <form class="form-inline float-right mt-4">
          <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <hr />
        <div class="container text-left">
          <a href="<%=request.getContextPath()%>/new" class="btn btn-success"
            >Add New Note</a
          >
          <a href="<%=request.getContextPath()%>/asc" class="btn btn-link"
            >Sort Asc</a
          >
          <a href="<%=request.getContextPath()%>/desc" class="btn btn-link"
            >Sort Desc</a
          >
          <a href="<%=request.getContextPath()%>/listbyuser" class="btn btn-link"
            >List by User</a
          >
        </div>
        
        <br />
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Title</th>
              <th>Text</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="note" items="${listNote}">
              <tr>
                <td><c:out value="${note.id}" /></td>
                <td><c:out value="${note.name}" /></td>
                <td><c:out value="${note.title}" /></td>
                <td><c:out value="${note.text}" /></td>
                <td>
                  <a href="edit?id=<c:out value='${note.id}' />">Edit</a>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <a href="delete?id=<c:out value='${note.id}' />">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>
