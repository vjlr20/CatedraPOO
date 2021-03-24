-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-03-2021 a las 18:12:14
-- Versión del servidor: 10.1.35-MariaDB
-- Versión de PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `catedra`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bitacora`
--

CREATE TABLE `bitacora` (
  `bitacora_id` int(11) NOT NULL,
  `caso_id` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `progreso` int(11) DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `bitacora`
--

INSERT INTO `bitacora` (`bitacora_id`, `caso_id`, `descripcion`, `progreso`, `fecha_registro`) VALUES
(1, 6, 'sdadsasdasdasdasdasdasdasdasd', NULL, '2021-03-23 23:21:43');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caso`
--

CREATE TABLE `caso` (
  `caso_id` int(11) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `solicitud_id` int(11) NOT NULL,
  `caso_descripcion` text NOT NULL,
  `programador` int(11) NOT NULL,
  `fecha_limite` date NOT NULL,
  `fecha_observaciones` date DEFAULT NULL,
  `tester` int(11) NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `caso`
--

INSERT INTO `caso` (`caso_id`, `codigo`, `solicitud_id`, `caso_descripcion`, `programador`, `fecha_limite`, `fecha_observaciones`, `tester`, `fecha_registro`, `estado`) VALUES
(6, 'VENT210323-789-2121-789', 1, 'asdasdsasd', 19, '2021-03-26', NULL, 23, '2021-03-23 23:19:47', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE `departamento` (
  `departamento_id` int(11) NOT NULL,
  `codigo` varchar(15) DEFAULT NULL,
  `departamento` varchar(30) NOT NULL,
  `jefe` int(11) DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`departamento_id`, `codigo`, `departamento`, `jefe`, `fecha_registro`) VALUES
(1, 'FINA', 'Finanzas', 22, '2021-03-14 20:58:35'),
(2, 'VENT', 'Ventas', 8, '2021-03-14 20:58:35'),
(3, 'FFIJ', 'Facturación fija', 22, '2021-03-14 20:58:56'),
(4, 'FMOV', 'Facturación móvil', 18, '2021-03-14 20:58:56'),
(5, 'FINT', 'Facturación Internet', 20, '2021-03-23 20:15:03'),
(6, 'LOGI', 'Logistica', 18, '2021-03-23 23:54:46');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento_empleados`
--

CREATE TABLE `departamento_empleados` (
  `departamento_emple_id` int(11) NOT NULL,
  `empleado` int(11) NOT NULL,
  `departamento` int(11) NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `departamento_empleados`
--

INSERT INTO `departamento_empleados` (`departamento_emple_id`, `empleado`, `departamento`, `fecha_registro`) VALUES
(1, 23, 3, '2021-03-23 23:14:35');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_caso`
--

CREATE TABLE `estado_caso` (
  `estado_caso_id` int(11) NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estado_caso`
--

INSERT INTO `estado_caso` (`estado_caso_id`, `estado`) VALUES
(1, 'Devuelto con observaciones'),
(2, 'Esperando aprobación del área solicitante'),
(3, 'Aprobado'),
(4, 'Vencido'),
(5, 'Asignado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_solicitud`
--

CREATE TABLE `estado_solicitud` (
  `estado_solicitud_id` int(11) NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estado_solicitud`
--

INSERT INTO `estado_solicitud` (`estado_solicitud_id`, `estado`) VALUES
(1, 'Aceptada'),
(2, 'Rechazada'),
(3, 'En espera');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_usuario`
--

CREATE TABLE `estado_usuario` (
  `estado_usuario_id` int(11) NOT NULL,
  `estado` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estado_usuario`
--

INSERT INTO `estado_usuario` (`estado_usuario_id`, `estado`) VALUES
(1, 'Activo'),
(2, 'Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `observaciones`
--

CREATE TABLE `observaciones` (
  `observacion_id` int(11) NOT NULL,
  `caso` int(11) NOT NULL,
  `observacion` text NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `solicitud_id` int(11) NOT NULL,
  `departamento_id` int(11) NOT NULL,
  `tipo_solicitud` int(11) NOT NULL,
  `caso` varchar(50) NOT NULL,
  `descripcion` text NOT NULL,
  `observaciones` text,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `solicitud`
--

INSERT INTO `solicitud` (`solicitud_id`, `departamento_id`, `tipo_solicitud`, `caso`, `descripcion`, `observaciones`, `fecha_registro`, `estado`) VALUES
(1, 3, 1, 'Nuevo sistema de gestión de ventas', 'Se necesita un sistema que permita gestionar las ventas con las función de generar reportes de manera mensual', NULL, '2021-03-18 20:00:05', 3),
(2, 3, 1, 'Generación de reporte mensual de facturaciones', 'Se necesita generar un reporte por mes de todas las facturas generadas por los servicios moviles.', NULL, '2021-03-18 20:49:47', 1),
(3, 3, 2, 'Cambio a sistema de ventas actual', 'Se necesita poder generar reportes de manera semanal, así mismo generar una planilla', 'Nel', '2021-03-18 20:52:31', 2),
(4, 3, 2, 'Update de factura de telefonia fija', 'Nuevo formato para factura de telefonia fija', 'Porque sí', '2021-03-23 21:17:32', 2),
(5, 6, 1, 'Sistema para control de logistica', 'Nuevo sistema para control de logistica en el area', NULL, '2021-03-24 02:30:02', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_solicitud`
--

CREATE TABLE `tipo_solicitud` (
  `tipo_solicitud_id` int(11) NOT NULL,
  `tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_solicitud`
--

INSERT INTO `tipo_solicitud` (`tipo_solicitud_id`, `tipo`) VALUES
(1, 'Sistema nuevo'),
(2, 'Cambio a sistema actual');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `tipo_id` int(11) NOT NULL,
  `tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`tipo_id`, `tipo`) VALUES
(1, 'Administrador'),
(2, 'Jefes de áreas funcionales'),
(3, 'Empleados de las áreas funcionales'),
(4, 'Jefe de desarrollo'),
(5, 'Programadores');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usuario_id` int(11) NOT NULL,
  `tipo_usuario` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(70) NOT NULL,
  `correo` varchar(200) NOT NULL,
  `contraseña` varchar(200) NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario_id`, `tipo_usuario`, `usuario`, `nombres`, `apellidos`, `correo`, `contraseña`, `fecha_registro`, `estado`) VALUES
(1, 1, 'admin', 'root', 'manager', 'root@root.com', 'root', '2021-03-14 20:52:21', 1),
(2, 1, 'admin2', 'Victor', 'López', 'lopez@gmail.com', '123456', '2021-03-14 20:54:03', 1),
(5, 1, 'victor', 'Victor', 'López', 'lopez.rivera@gmail.com', '12356', '2021-03-14 20:55:44', 2),
(8, 2, 'jose.lopez', 'José', 'López', 'jose@gmail.com', '12345678', '2021-03-18 03:14:31', 1),
(15, 4, 'george.rodriguez', 'Geroge', 'Rodriguez', 'george@gmail.com', 'George1999', '2021-03-18 03:36:59', 1),
(16, 4, 'victor.lopez', 'Victor', 'López', 'lopez.victor20@outlook.com', 'Victor', '2021-03-18 03:40:18', 1),
(18, 2, 'oscar.duarte', 'Oscar', 'Duarte', 'osacar@gmail.com', '123456', '2021-03-18 19:49:00', 1),
(19, 5, 'leonel.moreira', 'Leonel', 'Moreira', 'leonel@outlook.com', 'leonael', '2021-03-18 19:50:30', 1),
(20, 2, 'carlos.perez', 'Carlos', 'Perez', 'carlos@gmail.com', '456123', '2021-03-18 19:52:02', 1),
(21, 4, 'rafael.torres', 'Rafael', 'Torres', 'rafael.torres@udb.edu.ev', '123456', '2021-03-20 00:39:32', 1),
(22, 2, 'diana.funes', 'Diana', 'Funes', 'dianafunes98@gmail.com', '123456', '2021-03-21 02:04:43', 1),
(23, 3, 'oscar.lovos', 'Oscar', 'Lovos', 'oskrlovos@gmail.com', '123456', '2021-03-23 23:08:48', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bitacora`
--
ALTER TABLE `bitacora`
  ADD PRIMARY KEY (`bitacora_id`),
  ADD KEY `caso_id` (`caso_id`);

--
-- Indices de la tabla `caso`
--
ALTER TABLE `caso`
  ADD PRIMARY KEY (`caso_id`),
  ADD KEY `solicitud_id` (`solicitud_id`),
  ADD KEY `programador` (`programador`),
  ADD KEY `tester` (`tester`),
  ADD KEY `estado` (`estado`);

--
-- Indices de la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`departamento_id`),
  ADD KEY `jefe` (`jefe`);

--
-- Indices de la tabla `departamento_empleados`
--
ALTER TABLE `departamento_empleados`
  ADD PRIMARY KEY (`departamento_emple_id`),
  ADD KEY `empleado` (`empleado`),
  ADD KEY `departamento` (`departamento`);

--
-- Indices de la tabla `estado_caso`
--
ALTER TABLE `estado_caso`
  ADD PRIMARY KEY (`estado_caso_id`);

--
-- Indices de la tabla `estado_solicitud`
--
ALTER TABLE `estado_solicitud`
  ADD PRIMARY KEY (`estado_solicitud_id`);

--
-- Indices de la tabla `estado_usuario`
--
ALTER TABLE `estado_usuario`
  ADD PRIMARY KEY (`estado_usuario_id`);

--
-- Indices de la tabla `observaciones`
--
ALTER TABLE `observaciones`
  ADD PRIMARY KEY (`observacion_id`),
  ADD KEY `caso` (`caso`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`solicitud_id`),
  ADD KEY `departamento_id` (`departamento_id`),
  ADD KEY `tipo_solicitud` (`tipo_solicitud`),
  ADD KEY `estado` (`estado`);

--
-- Indices de la tabla `tipo_solicitud`
--
ALTER TABLE `tipo_solicitud`
  ADD PRIMARY KEY (`tipo_solicitud_id`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`tipo_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario_id`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD KEY `tipo_usuario` (`tipo_usuario`),
  ADD KEY `estado` (`estado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bitacora`
--
ALTER TABLE `bitacora`
  MODIFY `bitacora_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `caso`
--
ALTER TABLE `caso`
  MODIFY `caso_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `departamento`
--
ALTER TABLE `departamento`
  MODIFY `departamento_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `departamento_empleados`
--
ALTER TABLE `departamento_empleados`
  MODIFY `departamento_emple_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `estado_caso`
--
ALTER TABLE `estado_caso`
  MODIFY `estado_caso_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `estado_solicitud`
--
ALTER TABLE `estado_solicitud`
  MODIFY `estado_solicitud_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `estado_usuario`
--
ALTER TABLE `estado_usuario`
  MODIFY `estado_usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `observaciones`
--
ALTER TABLE `observaciones`
  MODIFY `observacion_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `solicitud_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tipo_solicitud`
--
ALTER TABLE `tipo_solicitud`
  MODIFY `tipo_solicitud_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `tipo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bitacora`
--
ALTER TABLE `bitacora`
  ADD CONSTRAINT `bitacora_ibfk_1` FOREIGN KEY (`caso_id`) REFERENCES `caso` (`caso_id`);

--
-- Filtros para la tabla `caso`
--
ALTER TABLE `caso`
  ADD CONSTRAINT `caso_ibfk_1` FOREIGN KEY (`solicitud_id`) REFERENCES `solicitud` (`solicitud_id`),
  ADD CONSTRAINT `caso_ibfk_2` FOREIGN KEY (`programador`) REFERENCES `usuarios` (`usuario_id`),
  ADD CONSTRAINT `caso_ibfk_3` FOREIGN KEY (`tester`) REFERENCES `usuarios` (`usuario_id`),
  ADD CONSTRAINT `caso_ibfk_4` FOREIGN KEY (`estado`) REFERENCES `estado_caso` (`estado_caso_id`);

--
-- Filtros para la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD CONSTRAINT `departamento_ibfk_1` FOREIGN KEY (`jefe`) REFERENCES `usuarios` (`usuario_id`);

--
-- Filtros para la tabla `departamento_empleados`
--
ALTER TABLE `departamento_empleados`
  ADD CONSTRAINT `departamento_empleados_ibfk_1` FOREIGN KEY (`empleado`) REFERENCES `usuarios` (`usuario_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `departamento_empleados_ibfk_2` FOREIGN KEY (`departamento`) REFERENCES `departamento` (`departamento_id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `observaciones`
--
ALTER TABLE `observaciones`
  ADD CONSTRAINT `observaciones_ibfk_1` FOREIGN KEY (`caso`) REFERENCES `caso` (`caso_id`);

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`departamento_id`) REFERENCES `departamento` (`departamento_id`),
  ADD CONSTRAINT `solicitud_ibfk_2` FOREIGN KEY (`tipo_solicitud`) REFERENCES `tipo_solicitud` (`tipo_solicitud_id`),
  ADD CONSTRAINT `solicitud_ibfk_3` FOREIGN KEY (`estado`) REFERENCES `estado_solicitud` (`estado_solicitud_id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`tipo_usuario`) REFERENCES `tipo_usuario` (`tipo_id`),
  ADD CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`estado`) REFERENCES `estado_usuario` (`estado_usuario_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
