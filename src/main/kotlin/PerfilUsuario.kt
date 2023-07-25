import jdk.jshell.Snippet.Status

//clase perfilUsuario con sus propiedades
class PerfilUsuario (
    val ID: Int,
    val Names: String,
    val LastNames: String,
    val UrlPhoto: String?,
    val Age: Int,
    val Email: String,
    val Bio: String?,
    var Status: String,
    val Hobbies: MutableList<Hobby> = mutableListOf(),
){
    //funciones para agregar, eliminar y actualizar hobbies
    fun agregarHobby(hobby: Hobby){
        Hobbies.add(hobby)
    }
    fun eliminarHobby(hobby: Hobby){
        Hobbies.remove(hobby)
    }
    fun actualizarHobby(hobby: Hobby){
        val index = Hobbies.indexOf(hobby)
        if (index != -1){
            Hobbies[index] = hobby
        }
    }
}
// clase para crear un hobby
class Hobby(
    val Title: String,
    val Description: String,
    val UrlPhoto: String?,
)
//main
fun main(args: Array<String>) {
    val listaPerfiles = mutableListOf<PerfilUsuario>()

    // Perfiles precargados para pruebas
    val perfil1 = PerfilUsuario(
        ID = 1,
        Names = "Erick",
        LastNames = "Barrera",
        UrlPhoto = "https://ejemploErick.com/foto_perfil.jpg",
        Age = 30,
        Email = "bar98342@uvg.edu.gt.com",
        Bio = "Hola! Soy Erick y me gustan los videojuegos.",
        Status = "Activo"
    )
    val perfil2 = PerfilUsuario(
        ID = 2,
        Names = "Pablo",
        LastNames = "Alvarez",
        UrlPhoto = null,
        Age = 25,
        Email = "alv221082@uvg.edu.gt.com",
        Bio = null,
        Status = "Inactivo"
    )

    listaPerfiles.add(perfil1)
    listaPerfiles.add(perfil2)

    var continuar = true

    //menu
    while (continuar) {
        println("Bienvenido! Selecciona una opción:")
        println("1. Crear perfil")
        println("2. Buscar perfil de usuario(s)")
        println("3. Eliminar perfil")
        println("4. Agregar Hobby")
        println("5. Salir")
        //opcion
        val opcion = readLine()?.toIntOrNull()

        //switch para las opciones
        when (opcion) {
            1 -> {
                val perfilNuevo = crearPerfil()
                listaPerfiles.add(perfilNuevo)
                println("Perfil creado exitosamente.")
            }
            2 -> buscarPerfiles(listaPerfiles)
            3 -> eliminarPerfil(listaPerfiles)
            4 -> agregarHobby(listaPerfiles)
            5 -> continuar = false
            else -> println("Opción inválida. Intente nuevamente.")
        }
        //Impresion del menu
        println()
    }
}
//funcion para crear un perfil
fun crearPerfil(): PerfilUsuario {
    println("Ingrese la información del perfil:")
    print("ID: ")
    val id = readLine()?.toIntOrNull() ?: 0
    print("Nombres: ")
    val nombres = readLine() ?: ""
    print("Apellidos: ")
    val apellidos = readLine() ?: ""
    print("UrlPhoto (opcional): ")
    val urlPhoto = readLine()
    print("Edad: ")
    val edad = readLine()?.toIntOrNull() ?: 0
    print("Correo: ")
    val correo = readLine() ?: ""
    print("Biografía (opcional): ")
    val biografia = readLine()
    print("Estado (Activo, Pendiente o Inactivo): ")
    val estado = readLine() ?: "Pendiente"

    return PerfilUsuario(id, nombres, apellidos, urlPhoto, edad, correo, biografia, estado)
}
//funcion para buscar perfiles
fun buscarPerfiles(listaPerfiles: List<PerfilUsuario>) {
    println("Seleccione el criterio de búsqueda:")
    println("1. Buscar por ID")
    println("2. Buscar por Nombres y/o Apellidos")

    val opcion = readLine()?.toIntOrNull()

    //switch para las opciones
    when (opcion) {
        1 -> {
            print("Ingrese el ID a buscar: ")
            val idBusqueda = readLine()?.toIntOrNull() ?: 0
            val perfilEncontrado = listaPerfiles.find { it.ID == idBusqueda }
            if (perfilEncontrado != null) {
                mostrarPerfil(perfilEncontrado)
            } else {
                println("Perfil no encontrado.")
            }
        }
        2 -> {
            print("Ingrese los nombres y/o apellidos a buscar: ")
            val terminoBusqueda = readLine()?.toLowerCase() ?: ""
            val perfilesEncontrados = listaPerfiles.filter {
                it.Names.toLowerCase().contains(terminoBusqueda) ||
                        it.LastNames.toLowerCase().contains(terminoBusqueda)
            }
            if (perfilesEncontrados.isNotEmpty()) {
                perfilesEncontrados.forEach { mostrarPerfil(it) }
            } else {
                println("Perfil no encontrado.")
            }
        }
        else -> println("Opción inválida. Intente nuevamente.")
    }
}
//funcion para eliminar perfiles
fun eliminarPerfil(listaPerfiles: MutableList<PerfilUsuario>) {
    print("Ingrese el ID del perfil a eliminar: ")
    val idEliminar = readLine()?.toIntOrNull() ?: 0
    val perfilEncontrado = listaPerfiles.find { it.ID == idEliminar }
    if (perfilEncontrado != null) {
        listaPerfiles.remove(perfilEncontrado)
        println("Perfil eliminado exitosamente.")
    } else {
        println("Perfil no encontrado.")
    }
}
//funcion para agregar hobbies
fun agregarHobby(listaPerfiles: MutableList<PerfilUsuario>) {
    print("Ingrese el ID del perfil al que desea agregar un hobby: ")
    val idPerfil = readLine()?.toIntOrNull() ?: 0
    val perfilEncontrado = listaPerfiles.find { it.ID == idPerfil }
    if (perfilEncontrado != null) {
        val hobby = crearHobby()
        perfilEncontrado.agregarHobby(hobby)
        println("Hobby agregado exitosamente al perfil.")
    } else {
        println("Perfil no encontrado.")
    }
}

//funcion para crear un hobby
fun crearHobby(): Hobby {
    println("Ingrese la información del hobby:")
    print("Título: ")
    val titulo = readLine() ?: ""
    print("Descripción: ")
    val descripcion = readLine() ?: ""
    print("UrlPhoto (opcional): ")
    val urlPhoto = readLine()

    return Hobby(titulo, descripcion, urlPhoto)
}
//funcion para mostrar el perfil
fun mostrarPerfil(perfil: PerfilUsuario) {
    println("ID: ${perfil.ID}")
    println("Nombres: ${perfil.Names}")
    println("Apellidos: ${perfil.LastNames}")
    println("UrlPhoto: ${perfil.UrlPhoto}")
    println("Edad: ${perfil.Age}")
    println("Correo: ${perfil.Email}")
    println("Biografía: ${perfil.Bio}")
    println("Estado: ${perfil.Status}")
    if (perfil.Hobbies.isNotEmpty()) {
        println("Hobbies:")
        for (hobby in perfil.Hobbies) {
            println("- ${hobby.Title}: ${hobby.Description} - ${hobby.UrlPhoto ?: "Sin foto"}")
        }
    }
}