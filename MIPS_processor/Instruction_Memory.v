
module instruction_memory(Address, Ins);

	input		[31:0]	Address;
	output reg	[31:0]	Ins;

	always@(1) begin

	case(Address[6:0])
		0:Ins[31:0] = 32'h8C110280;	// lw $s1, (data_memory.mem[20])
		1:Ins[31:0] = 32'h8C1202A0;	// lw $s2, (data_memory.mem[21])
		2:Ins[31:0] = 32'h8C1302C0;	// lw $s3, (data_memory.mem[22])
		3:Ins[31:0] = 32'h8C1402E0;	// lw $s4, (data_memory.mem[23])
		4:Ins[31:0] = 32'h02328020;	// add $s0, $s1, $s2
		5:Ins[31:0] = 32'h0251A822;	// sub $s5, $s2, $s1
		6:Ins[31:0] = 32'h22760009;	// addi $s6, $s3, 9
		7:Ins[31:0] = 32'h0200B82A;	// slt $s7, $s0, $0
		8:Ins[31:0] = 32'h12F40880;	// beq $s7, $s4, 
		9:Ins[31:0] = 32'hAC100300;	// sw $s0, (data_memory.mem[24])
	endcase

	end
endmodule
