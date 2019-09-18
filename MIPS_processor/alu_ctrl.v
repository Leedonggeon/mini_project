module alu_ctrl(instr, alu_op, alu_ctrl);
	
	input	[5:0]	instr;	
	input	[1:0]	alu_op;
	output	[2:0]	alu_ctrl);
	reg	[2:0]	alu_ctrl);	
	
always@(1) begin
		case(alu_op)
			2'b00: alu_ctrl <= 3'b010;//mem load, store
			2'b01: alu_ctrl <= 3'b110;//branch
			2'b10:
				case(instr[5:0])
					6'b000000: alu_ctrl <= 3'b010;//add
					6'b000001: alu_ctrl <= 3'b110;//sub
					6'b000010: alu_ctrl <= 3'b111;//slt
					6'b000011: alu_ctrl <= 3'b001;//and
					6'b000100: alu_ctrl <= 3'b011;//or
				endcase
		endcase
	end

endmodule