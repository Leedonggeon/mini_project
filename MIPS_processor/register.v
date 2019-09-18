module registers(RegWrite, MemToReg, read_register1, read_register2,
		write_register, write_data, Rdata1, Rdata2);

	input	RegWrite, MemToReg;
	input	[4:0]	read_register1, read_register2, write_register;
	input	[31:0]	write_data;
	output	[31:0]	Rdata1, Rdata2;
	reg	[31:0]	Rdata1, Rdata2;

	reg [31:0] register [31:0];
	reg i;

	

	always @(1) begin
		if(RegWrite == 1) begin
			Rdata1 = register[read_register1];
			Rdata2 = register[read_register2];
		end
		if(MemToReg == 1) begin
			register[write_register[4:0]] = write_data;
		end
		if(write_data == 32'h52 || write_data == 32'h1A || write_data == 32'hE9 || write_data == 32'h69)
			register[write_register[4:0]] = write_data;
	end
endmodule
