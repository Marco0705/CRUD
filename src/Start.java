import java.util.ArrayList;
import java.util.Scanner;

public class Start {

	// READ
	public static void mostrarUsuarios(BaseDeDatos bd) {
		System.out.println("************************************");
		ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
		usuarios = bd.R("SELECT * FROM usuarios");

		for (int i = 0; i < usuarios.size(); i++) {

			System.out.println(usuarios.get(i).getId() + "-" + usuarios.get(i).getNombre() + "-"
					+ usuarios.get(i).getPassword() + "-" + usuarios.get(i).getEdad() + "-"
					+ usuarios.get(i).getSalario() + "-" + usuarios.get(i).getActivo());
		}
		System.out.println("************************************");
	}

	public static void crearUsuario(BaseDeDatos bd) {

		Scanner entradaDatos = new Scanner(System.in);
		Usuarios u = new Usuarios();
		boolean estado = false;

		System.out.print("Introduce tu nombre: ");
		u.setNombre(entradaDatos.next());
		System.out.print("Introduce tu contraseña: ");
		u.setPassword(entradaDatos.next());
		System.out.print("Introduce tu edad: ");
		u.setEdad(entradaDatos.nextInt());
		System.out.print("Introduce tu salario: ");
		u.setSalario(entradaDatos.nextFloat());

		bd.C(u, "usuarios");

		if (!estado) {

			System.out.println("\nUsuario creado");

		} else {

			System.out.println("\nNo se ha creado");
		}

	}

	public static void borrarUsuario(BaseDeDatos bd) {

		mostrarUsuarios(bd);

		Scanner entradaDatos = new Scanner(System.in);

		boolean estado = false;
		int id = 0;

		System.out.print("Introduce tu id: ");
		id = entradaDatos.nextInt();

		bd.D("DELETE FROM usuarios WHERE id=" + id);

		if (!estado) {

			System.out.println("\nUsuario borrado\n");

		} else {

			System.out.println("\nNo se ha borrado\n");
		}

	}

	public static void modificarUsuario(BaseDeDatos bd) {

		Scanner entradaDatos = new Scanner(System.in);
		Usuarios u = new Usuarios();
		boolean estado = false;

		mostrarUsuarios(bd);

		System.out.print("Introduce el id: ");
		u.setId(entradaDatos.nextInt());
		System.out.print("Introduce tu nombre: ");
		u.setNombre(entradaDatos.next());
		System.out.print("Introduce tu contraseña: ");
		u.setPassword(entradaDatos.next());
		System.out.print("Introduce tu edad: ");
		u.setEdad(entradaDatos.nextInt());
		System.out.print("Introduce tu salario: ");
		u.setSalario(entradaDatos.nextFloat());

		bd.U(u, "usuarios");

		if (!estado) {

			System.out.println("\nUsuario modificado");

		} else {

			System.out.println("\nNo se ha modificado");
		}

	}

	public static void main(String[] args) {
		// CRUD Create, Read, Update, Delete

		Scanner entradaDatos = new Scanner(System.in);
		boolean result = false;
		Usuarios user = new Usuarios();
		BaseDeDatos bd = new BaseDeDatos("localhost", "ifp", "root", "");

		String usuario = "";
		String password = "";
		boolean tieneAcceso = false;
		int opcion = 0;

		do {

			System.out.print("\nIntroduce el usuario: ");
			usuario = entradaDatos.next();
			System.out.print("Introduce la contraseña: ");
			password = entradaDatos.next();
			tieneAcceso = bd.validar(usuario, password);

			if (!tieneAcceso) {

				System.out.println("\nNo tienes acceso\n");
			}

		} while (!tieneAcceso);

		System.out.println("\nUsuario y contraseña correctos\n");

		do {
			System.out.println("********************************************");
			System.out.println("*************GESTOR USUARIOS****************");
			System.out.println("********************************************");
			System.out.println("\nSeleccione una opción:\n");
			System.out.println("1) Crear usuario");
			System.out.println("2) Borrar usuario");
			System.out.println("3) Actualizar usuario");
			System.out.println("4) Listar usuarios");
			System.out.println("0) Salir");
			System.out.print("\nOpcion: ");

			opcion = entradaDatos.nextInt();

			if (opcion == 1) {

				crearUsuario(bd);

			} else if (opcion == 2) {

				borrarUsuario(bd);

			} else if (opcion == 3) {

				modificarUsuario(bd);

			} else if (opcion == 4) {

				System.out.println("********************************************");
				mostrarUsuarios(bd);
				System.out.println("********************************************");

			} else if (opcion != 0) {

				System.err.println("\nError, pulse una opción del menú");
			}
		} while (opcion != 0);
		System.out.println("\nFin del programa");

		// UPDATE
		/*
		 * user.setId(5); user.setNombre("Pepito Grillo"); user.setPassword("23456");
		 * user.setEdad(34); user.setSalario(1450); result = bd.U(user,"usuarios");
		 * 
		 * if (!result) { System.out.println("Actualizado correctamente"); } else {
		 * System.out.println("No se ha actualizado"); }
		 */

		// CREATE
		/*
		 * boolean result = false; Usuarios user= new Usuarios(); BaseDeDatos bd = new
		 * BaseDeDatos("localhost", "ifp", "root", ""); mostrarUsuarios(bd);
		 * 
		 * user.setNombre("Pepito"); user.setPassword("23456"); user.setEdad(34);
		 * user.setSalario(1450);
		 * 
		 * result = bd.C(user,"usuarios");
		 * 
		 * if (!result) {
		 * 
		 * System.out.println("Creado correctamente"); } else {
		 * System.out.println("No se ha creado"); }
		 */

		// DELETE
		/*
		 * result = bd.D("DELETE FROM usuarios WHERE id=1");
		 * 
		 * if (!result) {
		 * 
		 * System.out.println("Borrado correctamente"); } else {
		 * System.out.println("No se ha borrado"); }
		 */
		// mostrarUsuarios(bd);

		entradaDatos.close();
	}

}
