package categorias;

import java.util.Arrays;
import categorias.*;
import java.util.List;

import residencial.*;

public class ResidenciasConstantes
{
	R1 r1 = new R1();
	R2 r2 = new R2();
	R3 r3 = new R3();
	R4 r4 = new R4();
	R5 r5 = new R5();
	R6 r6 = new R6();
	R7 r7 = new R7();
	R8 r8 = new R8();
	R9 r9 = new R9();
	
	List <Categoria> categoriasResidenciales =  Arrays.asList(r1,r2,r3,r4,r5,r6,r7,r8);
	
	public List<Categoria> categoriasResidenciales()
	{
		return categoriasResidenciales;
	}
}
